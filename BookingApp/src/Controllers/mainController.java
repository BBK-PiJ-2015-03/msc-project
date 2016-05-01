package Controllers;

import Model.VehicleTypes;
import Utils.XmlParser;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import org.xml.sax.SAXException;
import Utils.TimeFieldValidator;
import resources.gmapsfx.GoogleMapView;
import resources.gmapsfx.MapComponentInitializedListener;
import resources.gmapsfx.javascript.object.GoogleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    private ComboBox<VehicleTypes> bookingVehicleType;
    @FXML
    private DatePicker bookingDateSelected;
    @FXML
    private ComboBox bookingPassengerNo;
    @FXML
    private TextField bookingTimeSelected;
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

    //Concurrency
    Service<Void> backgroundThread;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
        bookingDateSelected.setValue(LocalDate.now());
        bookingTimeSelected.setText(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
    }

    @FXML
    public void timeFieldValidation(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TimeFieldValidator tfv = new TimeFieldValidator();
            String time = tfv.format(bookingTimeSelected.getText());
            if(tfv.validate(time)){
                bookingTimeSelected.setText(time);
            } else {
                bookingTimeSelected.setText(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
            }
        }
    }

    @FXML
    public void calculateRouteButtonFired(){
        String origin = bookingFromNumberField.getPromptText()+" "+bookingFromAddressField.getText();
        String destination = bookingToNumberField.getPromptText()+" "+bookingToAddressField.getText();
        calculateRouteOnMap(origin,destination);
    }

    public void calculateRouteOnMap(String origin, String destination) {
        mapControll = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel);
        mapControll.newRoute(origin, destination);
        XmlParser x = new XmlParser();
        String journeyTime;
        try {
            journeyTime = x.getJourneyDuration(origin, destination);
        } catch (Exception e) {
            journeyTime = "Unavailable";
        }
        journeyDurationLabel.setText("Duration: " + journeyTime);
        journeyPickupFromLabel.setText("Pickup From: " + bookingFromAddressField.getText());
    }


    @FXML
    void addressKeyPressed(KeyEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        XmlParser x = new XmlParser();
                        String result, postCode;
                        postCode = ((TextField) event.getSource()).getText();
                        postCode = postCode.replaceAll("\\s", "");
                        Thread t = new Thread();
                        result = x.addressFromPostCode(postCode);
                        if (!result.equals(postCode.toUpperCase())) {
                            ((TextField) event.getSource()).setText(result);
                            updateMessage("Done !");
                        }
                        return null;
                    }
                };
            }
            };
        journeyClosestDriverLabel.textProperty().bind(backgroundThread.messageProperty());
        backgroundThread.restart();
        }
    }

        @Override
        public void mapInitialized() {
            MapController mc = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel);
            mc.mapInitialized();
        }


}



