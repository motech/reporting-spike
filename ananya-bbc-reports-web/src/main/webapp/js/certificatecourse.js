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
            dataGrid.buildPagination();
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
        $('#'+id+' thead').append(this.buildTableHeaderRow(header));
    }

    this.loadContent = function(contents){
        var id = this.tableId;
        $('#'+id+' tbody').find('tr').remove();
        for(var i in contents){
            var content = contents[i];
            $('#'+id+' tbody:last').append(this.buildTableContentRow(content));
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

    this.next = function(page, callback){
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
            callback();
        });
    }

    this.buildPagination = function() {
        var numPages = Math.ceil(this.count / this.rows);
        var paginationDiv = $('div.pagination ul');

        paginationDiv.find('li').remove();

        for (var i = 1; i <= numPages; ++i) {
            var link = $('<li><a>'+i+'</a></li>');
            paginationDiv.append(link);
        }

        var dataGrid = this;
        paginationDiv.find('a').click(function(event) {
            event.preventDefault();
            dataGrid.pageLinkClick(event.target);
        });
        
        paginationDiv.find('a:eq(0)').parent('li').addClass('active');
    }

    this.pageLinkClick = function(a){
        a = $(a);
        if(!a.parent('li').hasClass('active')){
            dataGrid.next(a.text(), function(){
                $('div.pagination').find('li').removeClass('active');
                a.parent('li').addClass('active');
            });
        }
    }

    this.handleError = function (msg){
        var errorEle = $('<div>'+msg+'</div>').addClass('alert alert-error');
        errorEle.append($('<a>x</a>').addClass('close').attr('data-dismiss','alert'));
        $('#'+this.tableId).parent().prepend(errorEle);
    }
    
    this.init(params);
}