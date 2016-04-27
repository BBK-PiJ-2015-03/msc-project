package Controllers;

import Utils.XmlParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
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
    private WebView mapView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine browser = liveMap.getEngine();
        mapView.getEngine().load(
                mainController.class.getResource("/Controllers/map.html").toExternalForm());
        browser.load("https://maps.google.co.uk");
    }

    public String mapPathBuilder(String from, String to){
        String url = "https://www.google.com/maps/embed/v1/directions?key=";
        String googleAPI = "AIzaSyB2kViW5AL4Q1vGaik53tkw6Ve1ZXdT2vg";
        String origin = "&origin=";
        String destination = "&destination=";
        String result = url+googleAPI+origin+from+destination+to;
        System.out.println(result);
        return result;

    }

    @FXML
    void fromAddressKeyPressed(KeyEvent event) {

    }

    @FXML
    void toAddressKeyPressed(KeyEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if(event.getCode() == KeyCode.ENTER) {
            XmlParser x = new XmlParser();
            String result = "";
            String postCode = bookingToAddressField.getText();
            postCode = postCode.replaceAll("\\s","");
            result = x.addressFromPostCode(postCode);
            if(result != null) {
                bookingToAddressField.setText(result);
            }
        }
    }
}



