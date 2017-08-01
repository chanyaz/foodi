/**
 * Created by asus on 7/31/2017.
 */

// necessary variables
var map;
var infoWindow;


function initialize() {
    var mapCanvas = document.getElementById("hostMap-canvas");
    var host = document.getElementById('host').value;
    var hostObject = JSON.parse(host);

    var myCenter=new google.maps.LatLng(hostObject.latitude, hostObject.longitude);
    var mapOptions = {center: myCenter, zoom: 15};
    map = new google.maps.Map(mapCanvas, mapOptions);

    // a new Info Window is created
    infoWindow = new google.maps.InfoWindow();

    // Event that closes the Info Window with a click on the map
    google.maps.event.addListener(map, 'click', function() {
        infoWindow.close();
    });

    // Finally displayMarkers() function is called to begin the markers creation
    var marker = new google.maps.Marker({
        map: map,
        position: myCenter,
    });


}
google.maps.event.addDomListener(window, 'load', initialize);


function displayMarkers(){

    // this variable sets the map bounds according to markers position
    var bounds = new google.maps.LatLngBounds();

    var hosts = document.getElementById('hosts').value;
    var hostObjects = JSON.parse(hosts);

    var latlng = new google.maps.LatLng(hostObj.latitude, hostObj.longitude);

    createMarker(latlng, hostObj);

    // marker position is added to bounds variable
    bounds.extend(latlng);

    // Finally the bounds variable is used to set the map bounds
    // with fitBounds() function

    //map.fitBounds(bounds);
}