/* Get all airports and add them to the dropdown menu for the fly form */
 function getLocationDropdown(){
     console.log("getting locations");

      $.ajax({
          url:"http://localhost:8080/api/airport/destinations/" + currentPlaneId,
          type:"get",
          success: function(locations) {
              console.log("These are the locations: " + locations);

              $("#destination-input").empty();

              $.each(locations, function() {
                 $("#destination-input").append($("<option />").val(this.id).text(this.city + " " + this.name));
              });
          }
      });
  }

$(document).ready(function(){
  $("#fly-form").validator();

  $('#fly-form').validator().on('submit', function (e) {
      if (e.isDefaultPrevented()) {
          // handle the invalid form...
      } else {
          // everything looks good!
          // Prevent submit to form
          e.preventDefault();
          fly();
      }
  });
});

var currentPlaneId;

function fly(){

    var inputDestination = $('#destination-input').val();

    $.ajax({
        url:"http://localhost:8080/api/plane/fly/" + currentPlaneId + "/" + inputDestination,
        type:"put",
        success: function(result){
            console.log("success");

            getPlanes();

            $("#fly-modal").modal("toggle");
        },
        error: function (jqXHR, exception) {
            var msg = '';
            if (jqXHR.status === 400) {
                msg = 'Not enough fuel';
            }
            $('#fly-error-message').html(msg);
        }
    });
}