package Controllers;

import Utils.XmlParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import org.xml.sax.SAXException;
import resources.gmapsfx.GoogleMapView;
import resources.gmapsfx.MapComponentInitializedListener;
import resources.gmapsfx.javascript.object.GoogleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, MapComponentInitializedListener {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab bookingTab;
    @FXML
    private TextField bookingFromNumberField;
    @FXML
    private TextField bookingFromAddressField;
    @FXML
    private TextField bookingToNumberField;
    @FXML
    private TextField bookingToAddressField;
    @FXML
    private SplitMenuButton bookingVehicleType;
    @FXML
    private SplitMenuButton bookingPassengerNo;
    @FXML
    private TextField bookingPassengerNameField;
    @FXML
    private TextField bookingPassengerTelField;
    @FXML
    private TextField bookingPassengerEmailField;
    @FXML
    private TextArea bookingCommentsField;
    @FXML
    private TextField bookingPriceField;
    @FXML
    private Button bookingAcceptButton;
    @FXML
    private Button bookingCancelButton;
    @FXML
    private Tab dispatchTab;
    @FXML
    private Tab liveMapTab;
    @FXML
    private WebView liveMap;
    @FXML
    private Tab driversTab;
    @FXML
    private Tab pricingTab;
    @FXML
    private Tab settingsTab;

    //Google Maps
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    private MapController mapControll;

    //Journey Route Details
    @FXML
    private Label journeyDistanceLabel;
    @FXML
    private Label journeyDurationLabel;
    @FXML
    private Label journeyPickupFromLabel;
    @FXML
    private Label journeyClosestDriverLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
//        WebEngine browser = liveMap.getEngine();
//        mapView.getEngine().load(
//                MainController.class.getResource("/Maps/map.html").toExternalForm());
//        browser.load("https://maps.google.co.uk");
    }

    @FXML
    public void calculateRouteButtonFired(){
        String origin = bookingFromNumberField.getPromptText()+" "+bookingFromAddressField.getText();
        String destination = bookingToNumberField.getPromptText()+" "+bookingToAddressField.getText();
        calculateRouteOnMap(origin,destination);
    }

    public void calculateRouteOnMap(String origin, String destination){
        mapControll = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel);
        mapControll.newRoute(origin,destination);

        XmlParser x = new XmlParser();
        String journeyTime;
        try {
            journeyTime = x.getJourneyDuration(origin,destination);
        } catch (Exception e) {
            journeyTime = "Unavailable";
        }
        journeyDurationLabel.setText("Duration: "+journeyTime);
        journeyPickupFromLabel.setText("Pickup From: "+bookingFromAddressField.getText());
    }


    @FXML
    void addressKeyPressed(KeyEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            XmlParser x = new XmlParser();
            String result = "", postCode = "";
            postCode = ((TextField) event.getSource()).getText();
            postCode = postCode.replaceAll("\\s", "");
            result = x.addressFromPostCode(postCode);
            if (result != null) {
                ((TextField) event.getSource()).setText(result);
            }
        }
    }

        @Override
        public void mapInitialized() {
            MapController mc = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel);
            mc.mapInitialized();
        }
}



