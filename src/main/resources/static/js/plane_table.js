$(document).ready(function(){
    /* Fill datatable */
    $('#plane-table').DataTable({
        columns: [
            {"data" : "serialNumber"},
            {"data" : "maxFuel" },
            {"data" : "currentFuel"}
        ]
    });

    getPlanes();
});

