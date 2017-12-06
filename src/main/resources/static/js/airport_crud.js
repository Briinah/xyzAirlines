/* Get all airports from the db */
function getAirports(){
    console.log("getting airports");

     $.ajax({
         url:"http://localhost:8080/api/airport/all",
         type:"get",
         success: function(airports) {
             console.log("These are the airports: " + airports);

             $('#airport-table').DataTable().clear();
             $('#airport-table').DataTable().rows.add(airports);
             $('#airport-table').DataTable().columns.adjust().draw();
         }
     });
}

/* Send api call to add airport */
function addAirport(){
    var inputLocation = $('#location-input').val();
    var inputPlanes = $('#planes-input').val();

    // create an array of animal id's
    var planeArray = [];
    $.each(inputPlanes, function(index, plane){
        planeArray[index] = {
            id: plane
        }
    })

    // create zookeeper object
    var airportObject = {
                location : inputLocation,
                planes : planeArray
    };

    var airport = JSON.stringify(airportObject);

    console.log("Adding airport: " + airport);

    $.ajax({
        url:"http://localhost:8080/api/airport/add",
        type:"post",
        data: airport,
        contentType: "application/json",
        success: function(result){
            console.log("success");
            getAirports();

            $("#airport-modal").modal("toggle");
        }
    });
}