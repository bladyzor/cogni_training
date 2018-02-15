$(document).ready(function(){
    $("input").focus(function(){
        $(this).css("background-color", "#bbd3f9");
    });
    $("input").blur(function(){
        $(this).css("background-color", "#ffffff");
    });
});

$(document).on('submit', '#myForm', function() {
    $.ajax({
        url     : $(this).attr('action'),
        type    : $(this).attr('method'),
        dataType: 'json',
        data    : $(this).serialize(),
        success : function( data ) {
            $('#result').html(data);
        },
        error   : function() {
            alert("ERROR");
        }
    });
    return false;
});