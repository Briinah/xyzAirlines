$(document).ready(function(){
    /* Fill datatable */
    $('#plane-table').DataTable({
        columns: [
            {"data" : "serialNumber"},
            {"data" : "type"},
            {"data" : "maxFuel" },
            {"data" : "currentFuel"},
            {"data" : "consumptionRate"},
            {"defaultContent" : "<button class='tank btn btn-default'>Tank</button>"},
            {"defaultContent" : "<button class='fly btn btn-default'>Fly</button>"}
        ]
    });

    getPlanes();

    var table = $('#plane-table').DataTable();

    $('#plane-table tbody').on( 'click', 'button.tank', function () {
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

    $('#plane-table tbody').on('click', 'button.fly' , function(){
        var data = table.row($(this).parents('tr')).data();

        console.log('fly!');
    });
});


