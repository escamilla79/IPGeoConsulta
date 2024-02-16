// Define the iniMap function
function iniMap(pLat ='19.4285', pLng ='-99.1277') {
	var coord = {
		lat : pLat,
		lng : pLng
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 10,
		center : coord
	});

	var marker = new google.maps.Marker({
		position : coord,
		map : map
	});
}

// API callback
function iniMapCallback(data) {
	if (data.error) {
		console.error("Error:", data.error.message);
	} else {
		// Process the data if successful
		console.log("Data:", data);
	}
}

function iniciarMap() {
	var coord = {
		lat : 19.437975,
		lng : -99.154723
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 10,
		center : coord
	});

	var marker = new google.maps.Marker({
		position : coord,
		map : map
	});
}

function iniMap2(pLat, pLng) {
	var coord = {
		lat : pLat,
		lng : pLng
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 10,
		center : coord
	});

	var marker = new google.maps.Marker({
		position : coord,
		map : map
	});
}

function initMap() {
	var geocoder = new google.maps.Geocoder();
	var latlng = {
		lat : 19.4285,
		lng : -99.1277
	};

	geocoder
			.geocode(
					{
						'location' : latlng
					},
					function(results, status) {
						if (status === 'OK') {
							if (results[0]) {
								document.getElementById('direccion').innerHTML = results[0].formatted_address;
							} else {
								window.alert('No se encontraron resultados');
							}
						} else {
							window.alert('Geocoder falló debido a: ' + status);
						}
					});
}
