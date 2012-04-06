buildTableContentRow = function(content, contentKeys){
    var newRow = $('<tr></tr>');
    for(var i in contentKeys){
        newRow.append('<td>'+content[contentKeys[i]]+'</td>')
    }

    return newRow;
}

buildTableHeaderRow = function(header, contentKeys){
    var newRow = $('<tr></tr>');
    for(var i in contentKeys){
        newRow.append('<th>'+header[contentKeys[i]]+'</th>')
    }

    return newRow;
}

fillDataIntoTable = function(id, data, header) {
    var contentKeys = new Array();
    for(var key in header){
        if(header.hasOwnProperty(key)) {
            contentKeys.push(key);
        }
    }


    if($('#'+id+' thead tr').length == 0){
        $('#'+id+' thead').append(buildTableHeaderRow(header, contentKeys));
    }

    $('#'+id+' tbody').find('tr').remove();
    var contents = data.content;
    for(var i in contents){
        var content = contents[i];
        $('#'+id+' tbody:last').append(buildTableContentRow(content, contentKeys));
    }
}


DataGrid = function(params){
    var rows;
    var dataUrl;
    var tableId;
    var header;
    var from;
    var to;
    var count;

    this.init = function(params){
        this.tableId = params['tableId']
        this.rows = params['rows'];
        this.dataUrl = params['dataUrl'];
        this.from = 0;
        this.to = this.from + this.rows;

        var dataGrid = this;
        $.ajax({
            url: this.dataUrl,
            data: 'from='+this.from+'&to='+this.to+'&header'+'&count',
            dataType: 'json'
        }).done(function(data){
            dataGrid.reload(data);
            dataGrid.buildPagination();
        });
    }

    this.next = function(page){
        this.from = (page - 1) * this.rows;
        this.to = this.from + this.rows;
        
        var dataGrid = this;
        $.ajax({
            url: this.dataUrl,
            data: 'from='+this.from+'&to='+this.to,
            dataType: 'json'
        }).done(function(data){
            dataGrid.reload(data);
        });
    }

    this.reload = function(data) {
        if(data.header){
            this.header = data.header;
        }
        if(data.count){
            this.count = data.count;
        }
        
        fillDataIntoTable(this.tableId, data, this.header);
    }

    this.buildPagination = function() {
        var numPages = this.count / this.rows;
        var dataGrid = this;
        $('div.pagination ul').find('li').remove();

        for (var i = 1; i <= numPages; ++i) {
            var link = $('<li><a>'+i+'</a></li>');
            $('div.pagination ul').append(link);
        }
        
        $('div.pagination').find('a').click(function(event) {
            event.preventDefault();
            dataGrid.pageLinkClick(event.target);
        });
        $('div.pagination').find('a:eq(0)').parent('li').addClass('active');
    }

    this.pageLinkClick = function(a){
        a = $(a);
        if(!a.parent('li').hasClass('active')){
            dataGrid.next(a.text());
        }
        $('div.pagination').find('li').removeClass('active');
        a.parent('li').addClass('active');
    }
    
    this.init(params);
}