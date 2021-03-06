package Controllers;

import Model.PricingImpl;
import Utils.XmlParser;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import resources.gmapsfx.GoogleMapView;
import resources.gmapsfx.MapComponentInitializedListener;
import resources.gmapsfx.javascript.object.*;
import resources.gmapsfx.service.directions.*;
import resources.gmapsfx.service.elevation.ElevationResult;
import resources.gmapsfx.service.elevation.ElevationServiceCallback;
import resources.gmapsfx.service.elevation.ElevationStatus;
import resources.gmapsfx.service.geocoding.GeocoderStatus;
import resources.gmapsfx.service.geocoding.GeocodingResult;
import resources.gmapsfx.service.geocoding.GeocodingService;
import resources.gmapsfx.service.geocoding.GeocodingServiceCallback;

import java.text.DecimalFormat;


public class MapController implements MapComponentInitializedListener,
        ElevationServiceCallback, GeocodingServiceCallback, DirectionsServiceCallback { //MapComponentInitializedListener {

    //Pointers to UI controls in main class
    private GoogleMapView mapView;
    private GoogleMap map;
    private Label journeyDistanceLabel;
    private Label journeyDurationLabel;
    private TextField price;


    protected DirectionsPane directions;
    private DirectionsRenderer renderer;
    private String journeyTime;
    private String journeyDistance;
    private String origin;
    private String destination;

    private static String unitOfDistance = "M";


    public String getJourneyTime() {
        return journeyTime;
    }

    public String getJourneyDistance() {
        return journeyDistance;
    }

    public MapController(GoogleMapView mapView, GoogleMap map, Label journeyDistanceLabel, Label journeyDurationLabel, TextField price){
        this.mapView = mapView;
        this.map = map;
        this.journeyDistanceLabel = journeyDistanceLabel;
        this.journeyDurationLabel = journeyDurationLabel;
        this.price = price;
    }

    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }

    public void setJourneyDistance(String journeyDistance) {
        this.journeyDistance = journeyDistance;
    }

    public void mapInitialized(){
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong center = new LatLong(51.528308, -0.3817765);

        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
//
        map = mapView.createMap(options);
//        directions = mapView.getDirec();

//        map.setHeading(123.2);
//        newRoute("E5 8AU", "N16 6LH");
    }

    public void newRoute(String origin, String destination){
        this.origin = origin;
        this.destination = destination;
        MapOptions options = new MapOptions();
        LatLong center = new LatLong(51.528308, -0.3817765);
        options.center(center)
                .mapMarker(true)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
//
        map = mapView.createMap(options);
        directions = mapView.getDirec();
        DirectionsService ds = new DirectionsService();
        renderer = new DirectionsRenderer(true, map, directions);
        DirectionsRequest dr = new DirectionsRequest(
                origin,
                destination,
                TravelModes.DRIVING);
        ds.getRoute(dr, this, renderer);
    }

    private void setDuration(String origin, String destination){
        XmlParser x = new XmlParser();
        String journeyTime;
        try {
            journeyTime = x.getJourneyDuration(origin, destination);
        } catch (Exception e) {
            journeyTime = "Unavailable";
        }
        setJourneyTime(journeyTime);
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        if(status.equals(DirectionStatus.OK)){
            DecimalFormat df = new DecimalFormat("#.00");
            DirectionsResult e = results;
            GeocodingService gs = new GeocodingService();
            System.out.println("SIZE ROUTES: " + e.getRoutes().size() + "\n" + "ORIGIN: " + e.getRoutes().get(0).getLegs().get(0).getStartLocation());
//            System.out.println("LEGS SIZE: " + e.getRoutes().get(0).getLegs().size());
//            System.out.println("WAYPOINTS " +e.getGeocodedWaypoints().size());
            try{
                setJourneyDistance(e.getRoutes().get(0).getLegs().get(0).getDistance().getText());

                setJourneyTime(e.getRoutes().get(0).getLegs().get(0).getDuration().getText());
//                System.out.println("Distancia total = " + journeyDistance);
//                System.out.println("Time total = " + journeyTime);
                setDuration(origin, destination);
                String calculatedPrice = df.format(PricingImpl.getInstance().calculatePrice(journeyDistance, journeyTime));
                price.setText(calculatedPrice);
                if (unitOfDistance.equals("KM")) {
                    journeyDistanceLabel.setText("Distance: " + journeyDistance);
                } else if(unitOfDistance.equals("M")) {
                    int sub = journeyDistance.indexOf(" ");
                    double distance = Double.parseDouble(journeyDistance.substring(0, sub));
                    distance = Math.round(distance * 0.621371);
                    journeyDistanceLabel.setText("Distance: " + distance + " miles");
                }
            } catch(Exception ex){
//                System.out.println("ERROR: " + ex.getMessage());
                ex.printStackTrace();
            }
//            System.out.println("Duration total = " + e.getRoutes().get(0).getLegs().get(0).getDuration().getText());
//            System.out.println("LEG(0)");
//            System.out.println(e.getRoutes().get(0).getLegs().get(0).getSteps().size());
//            System.out.println(renderer.toString());
//            journeyDistance = e.getRoutes().get(0).getLegs().get(0).getDistance().getText();
        }
    }

    @Override
    public void elevationsReceived(ElevationResult[] results, ElevationStatus status) {
        if(status.equals(ElevationStatus.OK)){
            for(ElevationResult e : results){
//                System.out.println(" Elevation on "+ e.getLocation().toString() + " is " + e.getElevation());
            }
        }
    }

    @Override
    public void geocodedResultsReceived(GeocodingResult[] results, GeocoderStatus status) {
        if(status.equals(GeocoderStatus.OK)){
            for(GeocodingResult e : results){
//                System.out.println(e.getVariableName());
//                System.out.println("GEOCODE: " + e.getFormattedAddress() + "\n" + e.toString());
            }

        }
    }
}


// Setting driver locations on map;
// Will come back to this when setting up the live map
//
// LatLong joeSmithLocation = new LatLong(47.6197, -122.3
// LatLong joshAndersonLocation = new LatLong(47.6297, -1
// LatLong bobUnderwoodLocation = new LatLong(47.6397, -1
// LatLong tomChoiceLocation = new LatLong(47.6497, -122.
// LatLong fredWilkieLocation = new LatLong(47.6597, -122
//
//
// //Set the initial properties of the map.
// MapOptions mapOptions = new MapOptions();
//
// mapOptions.center(new LatLong(47.6097, -122.3331))
//         .mapType(MapTypeIdEnum.ROADMAP)
//         .overviewMapControl(false)
//         .panControl(false)
//         .rotateControl(false)
//         .scaleControl(false)
//         .streetViewControl(false)
//         .zoomControl(false)
//         .zoom(12);
//
// map = mapView.createMap(mapOptions);
//
// //Add markers to the map
// MarkerOptions markerOptions1 = new MarkerOptions();
// markerOptions1.position(joeSmithLocation);
//
// MarkerOptions markerOptions2 = new MarkerOptions();
// markerOptions2.position(joshAndersonLocation);
//
// MarkerOptions markerOptions3 = new MarkerOptions();
// markerOptions3.position(bobUnderwoodLocation);
//
// MarkerOptions markerOptions4 = new MarkerOptions();
// markerOptions4.position(tomChoiceLocation);
//
// MarkerOptions markerOptions5 = new MarkerOptions();
// markerOptions5.position(fredWilkieLocation);
//
// Marker joeSmithMarker = new Marker(markerOptions1);
// Marker joshAndersonMarker = new Marker(markerOptions2)
// Marker bobUnderwoodMarker = new Marker(markerOptions3)
// Marker tomChoiceMarker= new Marker(markerOptions4);
// Marker fredWilkieMarker = new Marker(markerOptions5);
//
// map.addMarker( joeSmithMarker );
// map.addMarker( joshAndersonMarker );
// map.addMarker( bobUnderwoodMarker );
// map.addMarker( tomChoiceMarker );
// map.addMarker( fredWilkieMarker );
//
// InfoWindowOptions infoWindowOptions = new InfoWindowOp
// infoWindowOptions.content("<h2>Fred Wilkie</h2>"
//         + "Current Location: Safeway<br>"
//         + "ETA: 45 minutes" );
//
// InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWi
// fredWilkeInfoWindow.open(map, fredWilkieMarker);