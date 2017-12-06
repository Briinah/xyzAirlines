$(document).ready(function(){
    $("#plane-form").validator();

    $('#plane-form').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
        } else {
            // everything looks good!
            // Prevent submit to form
            e.preventDefault();
            addPlane();
        }
    });
});