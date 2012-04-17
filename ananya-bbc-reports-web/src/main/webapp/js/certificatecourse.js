$(document).ready(function(){
   $("#search_button").click(function(){
        new DataGrid({
            "tableId": "certificate_usage_report_table",
            "dataUrl": "report/certificatecourse/data",
            "rows": 10,
            "callback": function(){
                afterLoadingData();
            }
        });

        $("#certificate_usage_report_table").show();
   });

   $("#startDate").datepicker({
               startDate: '-90d',
               endDate: '+0d'
           });
   $("#endDate").datepicker();

   $("#startDate").blur(function (data) {
       var value = $(this).val();
       var enteredDate = new Date(value);
       var maxDate = new Date();
       var minDate = new Date();
       var daysToSubtract = 90;
       minDate.setDate(minDate.getDate() - daysToSubtract);
       minDate.setHours(0,0,0,0);
       minDate = minDate;
       if( value==""||enteredDate < minDate || enteredDate > maxDate){
            $("#start_date_error_message").show();
            $("#endDate").attr("disabled", "disabled");

       }
       else{
            $("#start_date_error_message").hide();
            $("#endDate").removeAttr("disabled");
       }
   });

   $("#endDate").blur(function(data){
       var value = $(this).val();
       var enteredDate = new Date(value);
       var minDate = new Date();
       var daysToSubtract =90;
       minDate = minDate.setDate(minDate.getDate() - daysToSubtract);
       if( value=="" || enteredDate < minDate || value < $("#startDate").val()){
           $("#end_date_error_message").show();
           $("#search_button").attr("disabled", "disabled");
       }
       else {
           $("#end_date_error_message").hide();
           $("#search_button").removeAttr("disabled");
       }
  });
});


afterLoadingData = function(){
    $("#certificate_usage_report_table").tablesorter();
}
