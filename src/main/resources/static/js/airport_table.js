$(document).ready(function(){
    /* Fill datatable */
    $('#airport-table').DataTable({
        columns: [
            {"data" : "location"},
            {"data" : "planes",
             "render":
                function(data, type, full, meta){
                    var text = "";
                    $.each(full.planes, function(index, value){

                        if(index > 0)
                            text += ", ";

                        text = text + " " + value.serialNumber;
                    });
                    return text;
                }
            }
        ]
    });

    getAirports();
});


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