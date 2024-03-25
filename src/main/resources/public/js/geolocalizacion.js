let lat, lon;

window.onload = function() {
    // Disable the submit button
    document.querySelector('button[type="submit"]').disabled = true;

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            lat = position.coords.latitude;
            lon = position.coords.longitude;

            // Enable the submit button
            document.querySelector('button[type="submit"]').disabled = false;
        }, function(error) {
            console.log("Error retrieving location: ", error);
        });
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function setCoordinates() {
    document.getElementById('latitud').value = lat;
    document.getElementById('longitud').value = lon;
}
