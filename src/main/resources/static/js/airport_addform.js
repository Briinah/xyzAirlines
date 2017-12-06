/* Get all animals and add them to the dropdown menu for the zookeeper form */
 function getPlaneDropdown(){
     console.log("getting planes");

      $.ajax({
          url:"http://localhost:8080/api/plane/all",
          type:"get",
          success: function(planes) {
              console.log("These are the planes: " + planes);

              $("#plane-input").empty();

              $.each(planes, function() {
                 console.log(this.serialNumber);
                 $("#plane-input").append($("<option />").val(this.id).text(this.serialNumber));
              });
          }
      });
  }