$(document).ready(function(){
   $("#filter_criteria").submit(function(event){
        event.preventDefault();
        if($(event.target).find('.error').length == 0){
            new DataGrid({
                "tableId": "certificate_usage_report_table",
                "dataUrl": "report/certificatecourse/data",
                "rows": 10,
                "baseSortBy" : "district"
            });

            $("#certificate_usage_report_table").show();
        }
   });

   $("#startDate").datepicker({
       startDate: '-90d',
       endDate: '+0d',
       "autoclose": true
   });

   $("#endDate").datepicker({
        startDate: '-90d',
        "autoclose": true
   });

   $("#endDate").blur(function(data){
      var endDateValue = $(this).val();
      var startDateValue = $("#startDate").val();
      var enteredStartDate = new Date(startDateValue);
      var enteredEndDate = new Date(endDateValue);
      var element = $('#endDate');
      element.parents('.control-group').removeClass('error');
      element.parents('.controls').children('.error-help').remove();
      if(endDateValue != "" && startDateValue!="" && enteredEndDate < enteredStartDate ){
         element.parents('.controls').append('<span class="help-inline error-help">"To Date" cannot be a date before "From Date".</span>');
         element.parents('.control-group').addClass('error');
      }
   });
 });
