window.onload = function() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            document.getElementById('latitud').value = position.coords.latitude;
            document.getElementById('longitud').value = position.coords.longitude;
        });
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}