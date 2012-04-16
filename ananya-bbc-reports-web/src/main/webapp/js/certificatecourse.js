$(document).ready(function(){
   $("#search_button").click(function(){
        new DataGrid({
            "tableId": "certificate_usage_report_table",
            "dataUrl": "report/certificatecourse/data",
            "rows": 10
        });

        $("#certificate_usage_report_table").show();
   });

   $("#startDate").datepicker({
               startDate: '-90d',
               endDate: '+0d'
           });
   $("#endDate").datepicker();

   $("#startDate").blur(function (data) {
         if ($(this).val() != "") {
           $("#endDate").removeAttr("disabled");
                }
         else {
           $("#endDate").attr("disabled", "disabled");
                }
              });
});
