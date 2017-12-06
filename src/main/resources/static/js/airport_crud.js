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