$(document).ready(function(){
    /* Fill datatable */
    $('#airport-table').DataTable({
        columns: [
            {"data" : "city"},
            {"data" : "name"},
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


