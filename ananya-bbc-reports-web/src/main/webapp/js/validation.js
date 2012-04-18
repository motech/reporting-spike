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


        var maxDate = new Date();
        var minDate = new Date();
        var daysToSubtract = 90;
        minDate.setDate(minDate.getDate() - daysToSubtract);
        minDate.setHours(0,0,0,0);
        var dateInputs = form.find('.date').each(function(index, element){
            element = $(element);
            var value = element.val();
            var enteredDate = new Date(value);
            if(value!="" && (enteredDate<minDate || (element.id == 'startDate' && enteredDate>maxDate))) {
                removeErrorMsg(element);
                element.parents('.controls').append('<span class="help-inline error-help">Please select date only within last 90 days.</span>');
                element.parents('.control-group').addClass('error');
                removeErrorMsgOnChange(element);
                result = false;
            }
        });



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

    removeErrorMsgOnChange = function(element){
        element = $(element);
        element.change(function(event){
            var element = $(event.target);
            removeErrorMsg(element);
        });
    }

    removeErrorMsg = function(element){
        element = $(element);
        element.parents('.control-group').removeClass('error');
        element.parents('.controls').children('.error-help').remove();
    }

})