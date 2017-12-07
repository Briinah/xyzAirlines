/* Get all planes and add them to the dropdown menu for the airport form */
 function getPlaneDropdown(){
     console.log("getting planes");

      $.ajax({
          url:"http://localhost:8080/api/plane/stationary",
          type:"get",
          success: function(planes) {
              console.log("These are the planes: " + planes);

              $("#planes-input").empty();

              $.each(planes, function() {
                 console.log(this.serialNumber);
                 $("#planes-input").append($("<option />").val(this.id).text(this.serialNumber));
              });
          }
      });
  }

$(document).ready(function(){
  $("#airport-form").validator();

  $('#airport-form').validator().on('submit', function (e) {
      if (e.isDefaultPrevented()) {
          // handle the invalid form...
      } else {
          // everything looks good!
          // Prevent submit to form
          e.preventDefault();
          addAirport();
          getPlaneDropdown();
      }
  });
});