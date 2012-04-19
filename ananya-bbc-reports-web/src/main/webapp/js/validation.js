$(document).ready(function(){
    $('form').submit(function(event){
        var form = $(event.target);
        var result = true;

        var trimSpaceInputs = form.find('.trim-space').each(function(index, element){
            element = $(element);
            element.val($.trim(element.val()));
        });

        var requiredInputs = form.find('.required').each(function(index, element){
            element = $(element);
            if(element.val() == "") {
                removeErrorMsg(element);
                element.parents('.controls').append('<span class="help-inline error-help">This is required.</span>');
                element.parents('.control-group').addClass('error');
                removeErrorMsgOnChange(element);
                result = false;
            }
        });


        var dateInputs = form.find('.date').each(function(index, element){
            element = $(element);
            var value = element.val();
            var enteredDate = new Date(value);
            var maxDate = parseDate(element.attr('max-date'));
            var minDate = parseDate(element.attr('min-date'));

            if(value!="" && (enteredDate < minDate || enteredDate > maxDate)) {
                removeErrorMsg(element);
                element.parents('.controls').append('<span class="help-inline error-help">Please select date only within last 90 days.</span>');
                element.parents('.control-group').addClass('error');
                removeErrorMsgOnChange(element);
                result = false;
            }
        });

        var dateRangeStartElement = $(form.find('.date-range-start')[0]);
        var dateRangeEndElement = $(form.find('.date-range-end')[0]);
        if(dateRangeStartElement && dateRangeStartElement){
            var dateRangeStart = new Date(dateRangeStartElement.val());
            var dateRangeEnd = new Date(dateRangeEndElement.val());
            if(dateRangeStart > dateRangeEnd){
                removeErrorMsg(dateRangeStartElement);
                removeErrorMsg(dateRangeEndElement);
                dateRangeEndElement.parents('.controls').append('<span class="help-inline error-help">"To Date" cannot be a date before "From Date".</span>');
                dateRangeEndElement.parents('.control-group').addClass('error');
                removeErrorMsgOnChange(dateRangeStartElement, dateRangeEndElement);
                result = false;
            }
        }

       var passwordElements = form.find('.check-password');
        if(passwordElements.length == 2 && $(passwordElements[0]).val() != $(passwordElements[1]).val()){
            passwordElements[0].value = passwordElements[1].value = '';
            passwordElements.each(function(index, element){
                element = $(element);
                removeErrorMsg(element);
                element.parents('.controls').append('<span class="help-inline error-help">Passwords do not match.</span>');
                element.parents('.control-group').addClass('error');
                removeErrorMsgOnChange(element);

            });
            result = false;
        }

        return result;
    });
    
     parseDate = function(date){
        var result;
        if(/^[-+]\d+[d]$/.test(date)){
            var nDays = parseInt(/([-+]\d+)([d])/g.exec(date)[1]);
            result = new Date();
            result.setDate(result.getDate() + nDays);
        }
        else if(date != null && date != undefined){
            result = new Date(date);
        }
        return result;
    }

    removeErrorMsgOnChange = function(element, otherElement){
        element = $(element);
        element.change(function(event){
            var element = $(event.target);
            if(otherElement) {
                removeErrorMsg(otherElement);
            }
            removeErrorMsg(element);
        });
    }

    removeErrorMsg = function(element){
        element = $(element);
        element.parents('.control-group').removeClass('error');
        element.parents('.controls').children('.error-help').remove();
    }

})