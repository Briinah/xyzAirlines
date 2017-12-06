$(document).ready(function(){
    /* Fill datatable */
    $('#plane-table').DataTable({
        columns: [
            {"data" : "serialNumber"},
            {"data" : "maxFuel" },
            {"data" : "currentFuel"},
            {"defaultContent" : "<button class='btn btn-default'>Tank</button>"}
        ]
    });

    getPlanes();

    var table = $('#plane-table').DataTable();

    $('#plane-table tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr') ).data();

        console.log(data['id']);

        $.ajax({
            url:"http://localhost:8080/api/plane/tank/" + data['id'],
            type:"put",
            success: function(result){
                console.log("tanked!");
                getPlanes();
            }
        });
    } );
});


