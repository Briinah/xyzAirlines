
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

/* Send api call to add plane */
function addPlane(){
    var planeSerialNo = $('#serial-input').val();
    var planeMaxFuel = $('#maxfuel-input').val();
    var planeType = $('#type-input').val();
    var planeCons = $('#consumption-input').val();

    var planeObject = {
        serialNumber : planeSerialNo,
        maxFuel: planeMaxFuel,
        type: planeType,
        consumptionRate: planeCons
    };

    var plane = JSON.stringify(planeObject);

    console.log("Adding plane: " + plane);

    $.ajax({
        url:"http://localhost:8080/api/plane/add",
        type:"post",
        data: plane,
        contentType: "application/json",
        success: function(result){
            console.log("success");
            getPlanes();
            $("#plane-modal").modal("toggle");
        }
    });
}

/* Sends api call to delete a plane with id */
function deletePlane(id){

    $.ajax({
        url:"http://localhost:8080/api/plane/delete/" + id,
        type:"delete",
        success: function(result){
            console.log("plane deleted");
            getPlanes();
        }
    });
}