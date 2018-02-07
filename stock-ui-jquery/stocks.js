$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/api/stocks"
	}).then(function(data) {
	  var items = [];
	  $.each( data, function( key, val ) {
		items.push('<tr><th scope="row">'+val.id +'</th><td>'+val.name+'</td><td>'+val.currentPrice+'</td><td>'+new Date(val.lastUpdate).toLocaleString("nl-NL")+'</td></tr>');
	  });
          $('#stocks').append(items);
	});
}); 
