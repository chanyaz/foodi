/**
 * Created by asus on 7/31/2017.
 */

// necessary variables
var map;
var infoWindow;


function initialize() {
    var mapCanvas = document.getElementById("map-canvas");
    var myCenter=new google.maps.LatLng(43.66386657178007,-79.39064025628909);
    var mapOptions = {center: myCenter, zoom: 10};
    map = new google.maps.Map(mapCanvas, mapOptions);

    // a new Info Window is created
    infoWindow = new google.maps.InfoWindow();

    // Event that closes the Info Window with a click on the map
    google.maps.event.addListener(map, 'click', function() {
        infoWindow.close();
    });

    // Finally displayMarkers() function is called to begin the markers creation
    displayMarkers();
}
google.maps.event.addDomListener(window, 'load', initialize);