/******
* DataGrid: Version 1.0
* This contains three objects: DataGrid, Pagination and GoToPage
* http://github.com/f1code/nithi/blob/master/data-grid.js
*/

DataGrid = function(params){
    var rows;
    var dataUrl;
    var tableId;
    var from;
    var to;
    var count;
    var contentKeys;
    var startDate, endDate;
    var baseSortBy;

    this.init = function(params){
        this.tableId = params['tableId'];
        this.rows = params['rows'];
        this.dataUrl = params['dataUrl'];
        this.from = 0;
        this.to = this.from + this.rows;
        this.fromDate = $("#startDate").val();
        this.toDate = $("#endDate").val();
        this.baseSortBy = params['baseSortBy'];
        this.district = $('#location_district').val();
        this.block = $('#location_block').val();
        this.panchayat = $('#location_panchayat').val();

        var dataGrid = this;
        this.sortTable = new SortTable({
            "tableId" : dataGrid.tableId,
            "onSort" : function(){
                dataGrid.next(1);
            },
            "baseSortBy" : dataGrid.baseSortBy
        });

        $.ajax({
            url: this.dataUrl,
            data: 'startDate='+this.fromDate+'&endDate='+this.toDate+'&from='+this.from+'&to='+this.to+'&header'+'&count'+'&location_district=' + this.district + '&location_block=' + this.block +  '&location_panchayat=' + this.panchayat +this.sortTable.sortParams(),
            dataType: 'json',
            error: function(){
                dataGrid.handleError("An error has occurred, please try again.")
            }
        }).done(function(data){
            dataGrid.count = data.count;
            dataGrid.extractContentKeys(data.header);
            dataGrid.loadHeader(data.header);
            dataGrid.loadContent(data.content);

            var numPages = Math.ceil(dataGrid.count / dataGrid.rows);

            if(numPages == 0){
                $('#' + dataGrid.tableId + '_pagination').find('.pagination').remove();
                $('#' + dataGrid.tableId + '_go_to_page').find('.form-search').remove();
            }
            if(numPages > 0){
                dataGrid.pagination = new Pagination({
                    "numPages" : numPages,
                    "showNumPages" : 4,
                    "where" : $('#' + dataGrid.tableId + '_pagination'),
                    "click" : function(pageNum){
                        dataGrid.next(pageNum);
                    }
                });

                if(numPages > 1){
                    new GoToPage({
                        "numPages" : numPages,
                        "where" : $('#' + dataGrid.tableId + '_go_to_page'),
                        "click" : function(pageNum){
                            dataGrid.next(pageNum);
                        }
                    });
                }
            }

            dataGrid.sortTable.refresh();
        });
    }

    this.extractContentKeys = function(header){
        this.contentKeys = new Array();
        for(var key in header){
            if(header.hasOwnProperty(key)) {
                this.contentKeys.push(key);
            }
        }
    }

    this.loadHeader = function(header){
        var id = this.tableId;
        $('#'+id+' thead').find('tr').remove();
        $('#'+id+' thead').append(this.buildTableHeaderRow(header));
    }

    this.loadContent = function(contents){
        var id = this.tableId;
        $('#'+id+' tbody').find('tr').remove();
        for(var i in contents){
            var content = contents[i];
            $('#'+id+' tbody:last').append(this.buildTableContentRow(content));
        }

        if(contents.length == 0){
            $('#'+id+' tbody:last').append('<tr><td class="alert alert-info" colspan='+this.contentKeys.length+' style="text-align:center">No Results Found!</td></tr>');
        }

    }

    this.buildTableContentRow = function(content){
        var contentKeys = this.contentKeys;
        var newRow = $('<tr></tr>');
        for(var i in contentKeys){
            newRow.append('<td>'+content[contentKeys[i]]+'</td>');
        }

        return newRow;
    }

    this.buildTableHeaderRow = function(header){
        var contentKeys = this.contentKeys;
        var newRow = $('<tr></tr>');
        for(var i in contentKeys){
            newRow.append('<th header-key="'+contentKeys[i]+'">'+header[contentKeys[i]]+'</th>');
        }

        return newRow;
    }

    this.next = function(page){
        this.from = (page - 1) * this.rows;
        this.to = this.from + this.rows;
        this.to = this.to > this.count ? this.count : this.to;
        this.fromDate = $("#startDate").val();
        this.toDate = $("#endDate").val();

        $('div.alert-error').remove();
        var dataGrid = this;
        $.ajax({
            url: this.dataUrl,
            data: 'startDate='+this.fromDate+'&endDate='+this.toDate+'&from='+this.from+'&to='+this.to+this.sortTable.sortParams(),
            dataType: 'json',
            error: function(){
                dataGrid.handleError("An error has occurred, please try again.")
            }
        }).done(function(data){
            dataGrid.loadContent(data.content);
            dataGrid.pagination.refresh(page);
        });
    }

    this.handleError = function(msg) {
        var errorEle = $('<div>'+msg+'</div>').addClass('alert alert-error');
        errorEle.append($('<a>x</a>').addClass('close').attr('data-dismiss','alert'));
        $('#'+this.tableId + '_error').append(errorEle);
    }

    this.updateParams = function(params){
        for(var key in params){
            if(params.hasOwnProperty(key) && this.hasOwnProperty(key)){
                this[key] = params[key];
            }
        }
    }

    this.init(params);
}

Pagination = function(params) {
    this.init = function (params) {
        this.paginationDiv = $('<div class="pagination"><ul></ul></div>');
        this.numPages = params.numPages;
        this.showNumPages = params.showNumPages;

        $(params.where).find('.pagination').remove();
        $(params.where).append(this.paginationDiv);
        var currentPage = params.currentPage ? params.currentPage : 1;

        this.construct(currentPage);
        this.refresh(currentPage);

        var pagination = this;
        this.paginationDiv.find('a').click(function(event){
            event.preventDefault();
            var a = $(event.target);
            if(!a.parent('li').hasClass('active') && !a.parent('li').hasClass('disabled')) {
                var pageNum = parseInt(a.attr('page'));
                params.click(pageNum);
            }
        });
    }

    this.construct = function(currentPage){
        var base = this.paginationDiv.find('ul');

        base.remove('li');

        base.append('<li title="'+1+'"><a page="'+1+'">&lt;&lt;</a></li>');
        base.append('<li><a page="'+(currentPage - 1)+'">&lt;</a></li>');

        var vicinity = this.getVicinityNumbers(this.showNumPages, 1, this.numPages, currentPage);

        for (var i = vicinity.start; i <= vicinity.end; ++i) {
            var link = $('<li><a page='+i+'>'+i+'</a></li>');
            base.append(link);
        }
        base.append('<li><a page="'+(currentPage + 1 )+'">&gt;</a></li>');
        base.append('<li title="'+this.numPages+'"><a page="'+this.numPages+'">&gt;&gt;</a></li>');
    }

    this.refresh = function(currentPage){
        var vicinity = this.getVicinityNumbers(this.showNumPages, 1, this.numPages, currentPage);
        var numAs = this.paginationDiv.find('a').length;
        var pagination = this;
        this.paginationDiv.find('a').each(function(index, a){
            a = $(a);
            var li = a.parent('li');

            li.removeClass('active');
            li.removeClass('disabled');

            if(index == 1){
                a.attr('page', currentPage - 1);
            } else if (index == numAs - 2){
                a.attr('page', currentPage + 1);
            } else if (index > 1 && index < numAs - 2) {
                a.attr('page', vicinity.start);
                a.text(vicinity.start);
                vicinity.start++;
            }

            var pageNum = a.attr('page');
            if (pageNum == currentPage){
                li.addClass('active');
            } else if (pageNum < 1 || pageNum > pagination.numPages) {
                li.addClass('disabled');
            }
        });
    }

    this.getVicinityNumbers = function(count, start, end, num) {
        if(count < 0 || end < start || (num < start || num > end)){
            return null;
        }

        var result = {"start": start, "end": end}
        if(end - start > count){
            var mid = count / 2;
            var mayBeStart = num - Math.ceil(mid) + 1;
            var mayBeEnd = num + Math.floor(mid);

            if(mayBeStart < start){
                mayBeEnd += (start - mayBeStart);
                mayBeStart = start;
            }

            if(mayBeEnd > end){
                mayBeStart -= (mayBeEnd - end);
                mayBeEnd = end;
            }

            result.start = mayBeStart;
            result.end = mayBeEnd;
        }

        return result;
    }

    this.init(params);
}

GoToPage = function(params){
    this.init = function(params){
        var goToPageForm = $('<form class="well-normal form-search">' +
                '                   <input class="search-query input-small" type="text" placeholder="'+1+' .. '+params.numPages+'">' +
                '                   <button class="btn" type="submit">Go</button>' +
                '               </form>');

        params.where.find('form').remove();
        params.where.append(goToPageForm);

        goToPageForm.submit(function(event){
            event.preventDefault();
            var pageNum = parseInt(goToPageForm.find('input:text').val());
            if(!isNaN(pageNum) && (pageNum >= 1 && pageNum <= params.numPages)) {
                params.click(pageNum);
            }
            goToPageForm.find('input:text').val('');
        });
    }

    this.init(params);
}

SortTable = function(params){
    this.init = function(params){
        this.table = $('#' + params.tableId);
        this.baseSortBy = params.baseSortBy;

        var self = this;
        this.table.undelegate('th','click');
        this.table.delegate('th', 'click', function(event){
            var th = $(event.target);
            var key = th.attr('header-key');

            if(key == self.baseSortBy){
                self.toggleSort(th);
            } else {
                var baseSortTh = self.table.find('th[header-key="'+self.baseSortBy+'"]');
                var baseSortOrder = self.getSortOrder(baseSortTh);
                var sortOrder = self.getSortOrder(th);

                self.table.find('th').removeClass('sort-order-asc');
                self.table.find('th').removeClass('sort-order-desc');

                if(sortOrder != ""){
                    th.addClass('sort-order-' + sortOrder);
                }
                baseSortTh.addClass('sort-order-' + baseSortOrder);

                self.toggleSort(th, 'sort-order-asc');
            }

            params.onSort();
        });
    }

    this.refresh = function(){
        var baseSortTh = this.table.find('th[header-key="'+this.baseSortBy+'"]');
        this.toggleSort(baseSortTh, 'sort-order-asc');
    }

    this.toggleSort = function(ele, defaultSortClass){
        ele = $(ele);
        if(ele.hasClass('sort-order-asc')){
            ele.removeClass('sort-order-asc');
            ele.addClass('sort-order-desc');
        } else if (ele.hasClass('sort-order-desc')){
            ele.removeClass('sort-order-desc');
            ele.addClass('sort-order-asc');
        } else {
            ele.addClass(defaultSortClass);
        }
    }

    this.sortParams = function(){
        var result = "";
        var baseSortOrder = this.getSortOrder(this.table.find('th[header-key="'+this.baseSortBy+'"]'));
        result += "&baseSortBy="+this.baseSortBy+"&baseSortOrder="+baseSortOrder;

        var otherSortBy = this.table.find('th[header-key!="'+this.baseSortBy+'"]').filter('.sort-order-asc,.sort-order-desc');
        if(otherSortBy.length != 0){
            var otherSortOrder = this.getSortOrder(otherSortBy);
            result += "&sortBy="+otherSortBy.attr('header-key')+"&sortOrder="+otherSortOrder;
        }

        return result;
    }

    this.getSortOrder = function(ele){
        ele = $(ele);
        if(ele.hasClass('sort-order-asc')){
            return "asc";
        } else if (ele.hasClass('sort-order-desc')){
            return "desc";
        } else {
            return "";
        }
    }

    this.init(params);
}