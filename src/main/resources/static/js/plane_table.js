$(document).ready(function(){
    /* Fill datatable */
    $('#plane-table').DataTable({
        columns: [
            {"data" : "serialNumber"},
            {"data" : "maxFuel" },
            {"date" : "currentFuel"}
        ]
    });

    getPlanes();
});


/* Get all airports from the db */
function getPlanes(){
    console.log("getting planes");

     $.ajax({
         url:"http://localhost:8080/api/plane/all",
         type:"get",
         success: function(planes) {
             console.log("These are the planes: " + planes);

             $('#plane-table').DataTable().clear();
             $('#plane-table').DataTable().rows.add(planes);
             $('#plane-table').DataTable().columns.adjust().draw();
         }
     });
}