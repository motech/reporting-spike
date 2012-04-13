DataGrid = function(params){
    var rows;
    var dataUrl;
    var tableId;
    var from;
    var to;
    var count;
    var contentKeys;

    this.init = function(params){
        this.tableId = params['tableId'];
        this.rows = params['rows'];
        this.dataUrl = params['dataUrl'];
        this.from = 0;
        this.to = this.from + this.rows;

        var dataGrid = this;
        $.ajax({
            url: this.dataUrl,
            data: 'from='+this.from+'&to='+this.to+'&header'+'&count',
            dataType: 'json',
            error: function(){
                dataGrid.handleError("An error has occurred, please try again.")
            }
        }).done(function(data){
            dataGrid.count = data.count;
            dataGrid.extractContentKeys(data.header);
            dataGrid.loadHeader(data.header);
            dataGrid.loadContent(data.content);

            new Pagination({
                "numPages" : Math.ceil(dataGrid.count / dataGrid.rows),
                "showNumPages" : 4,
                "where" : $('#' + dataGrid.tableId + '_pagination'),
                "click" : function(pageNum){
                    dataGrid.next(pageNum);
                }
            });
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
            newRow.append('<th>'+header[contentKeys[i]]+'</th>');
        }

        return newRow;
    }

    this.next = function(page){
        this.from = (page - 1) * this.rows;
        this.to = this.from + this.rows;
        this.to = this.to > this.count ? this.count : this.to;

        $('div.alert-error').remove();
        var dataGrid = this;
        $.ajax({
            url: this.dataUrl,
            data: 'from='+this.from+'&to='+this.to,
            dataType: 'json',
            error: function(){
                dataGrid.handleError("An error has occurred, please try again.")
            }
        }).done(function(data){
            dataGrid.loadContent(data.content);
        });
    }

    this.handleError = function(msg) {
        var errorEle = $('<div>'+msg+'</div>').addClass('alert alert-error');
        errorEle.append($('<a>x</a>').addClass('close').attr('data-dismiss','alert'));
        $('#'+this.tableId + '_error').append(errorEle);
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
                pagination.refresh(pageNum);
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

$(document).ready(function(){
   $("#search_button").click(function(){
        new DataGrid({
            "tableId": "certificate_usage_report_table",
            "dataUrl": "report/certificatecourse/data",
            "rows": 1
        });

        $("#certificate_usage_report_table").show();
    });
});