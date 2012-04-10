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

        var passwordElements = form.find('input:password');
        if(passwordElements.length == 2 && $(passwordElements[0]).val() != $(passwordElements[1]).val()){
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