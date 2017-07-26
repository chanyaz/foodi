// *
// * Add multiple markers
// * 2013 - en.marnoto.com
// *

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


// creating markers with createMarker function
function displayMarkers(){

    // this variable sets the map bounds according to markers position
    var bounds = new google.maps.LatLngBounds();

    var hosts = document.getElementById('hosts').value;
    var hostObjects = JSON.parse(hosts);

    for (var i = 0; i < hostObjects.length; i++){

        var hostObj = hostObjects[i];

        for (var j = 0; j < hostObj.hostAccesses.length; j++)
        {
            var hostAccessObj = hostObj.hostAccesses[j];

            var latlng = new google.maps.LatLng(hostAccessObj.latitude, hostAccessObj.longitude);
            var name = hostObj.hostName;
            var hostAccessId = hostAccessObj.hostAccessId;
            //var address1 = obj.address;
            //var telephone = obj.telephone;
            //var webSite = obj.webSite;
            //var description = obj.locationWeather.description;
            //var temp = obj.locationWeather.temp;
            //var humidity = obj.locationWeather.humidity;

            createMarker(latlng, name, hostAccessId);

            // marker position is added to bounds variable
            bounds.extend(latlng);

            // Finally the bounds variable is used to set the map bounds
            // with fitBounds() function
        }

        //map.fitBounds(bounds);


    }



}

// This function creates each marker and it sets their Info Window content
function createMarker(latlng, name, hostAccessId){
    var marker = new google.maps.Marker({
        map: map,
        position: latlng,
        title: name,
        hostAccessId: hostAccessId
    });


    // This event expects a click on a marker
    // When this event is fired the Info Window content is created
    // and the Info Window is opened.
    google.maps.event.addListener(marker, 'click', function() {

        showData(this);
    });

}

function jumpToLocation(obj)
{
    var latlng = new google.maps.LatLng(obj.value.split('#')[0], obj.value.split('#')[1]);
    map.setCenter(latlng);
}