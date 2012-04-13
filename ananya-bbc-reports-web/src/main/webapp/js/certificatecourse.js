$(document).ready(function(){
   $("#search_button").click(function(){
        new DataGrid({
            "tableId": "certificate_usage_report_table",
            "dataUrl": "report/certificatecourse/data",
            "rows": 10
        });

        $("#certificate_usage_report_table").show();
    });
});