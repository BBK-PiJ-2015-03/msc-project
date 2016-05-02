package Controllers;

import Model.BookingImpl;
import Model.Cash;
import Model.Interfaces.Account;
import Model.Interfaces.Driver;
import Utils.XmlParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    //Booking tab UI controls
    @FXML
    private Tab bookingTab;
    @FXML
    private Label bookingNumber;
    @FXML
    private ComboBox<Account> bookingAccountOrCash;
    @FXML
    private TextField bookingFromNumberField;
    @FXML
    private TextField bookingFromAddressField;
    @FXML
    private TextField bookingToNumberField;
    @FXML
    private TextField bookingToAddressField;
    @FXML
    private ComboBox<String> bookingVehicleType;
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

    //Booking tab map + route Details
    @FXML
    private WebView liveMap;
    @FXML
    private Label journeyDistanceLabel;
    @FXML
    private Label journeyDurationLabel;
    @FXML
    private Label journeyPickupFromLabel;
    @FXML
    private Label journeyClosestDriverLabel;

    //Dispatch tab table
    @FXML
    private TableView<BookingImpl> bookingTableView;
    @FXML
    private TableColumn<BookingImpl, String> timeCol;
    @FXML
    private TableColumn<BookingImpl, String> nameCol;
    @FXML
    private TableColumn<BookingImpl, String> pickUpCol;
    @FXML
    private TableColumn<BookingImpl, String> dropOffCol;
    @FXML
    private TableColumn<BookingImpl, String> commentCol;
    @FXML
    private TableColumn<BookingImpl, String> priceCol;



    // Driver tab UI controls
    @FXML
    private Tab driversTab;
    @FXML
    private ListView<Driver> driverTabList;
    @FXML
    private Button driverTabDeleteButton;
    @FXML
    private TextField driverNameField;
    @FXML
    private TextField driverIdField;
    @FXML
    private TextField driverAddressField;
    @FXML
    private TextField driverPhoneField;
    @FXML
    private TextField driverPinField;
    @FXML
    private TextField driverNisField;
    @FXML
    private TextField driverPcoNoField;
    @FXML
    private DatePicker driverPcoDateField;
    @FXML
    private TextField vehicleRegistrationField;
    @FXML
    private TextField vehicleColourField;
    @FXML
    private TextField vehicleTypeField;
    @FXML
    private DatePicker vehicleMotDateField;
    @FXML
    private DatePicker vehicleInsuranceDateField;
    @FXML
    private DatePicker vehiclePcoDateField;
    @FXML
    private Button driverTabSaveButton;
    @FXML
    private Button driverTabCancelButton;
    @FXML
    private TextField addNewDriverField;
    @FXML
    private Button addNewDriverButton;

    //Account tab UI controls
    @FXML
    private Tab accountTab;
    @FXML
    private ListView<Account> accountTabList;
    @FXML
    private Button accountTabDeleteButton;
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField accountIdField;
    @FXML
    private TextField accountAddressField;
    @FXML
    private TextField accountPhoneField;
    @FXML
    private Button accountTabSaveButton;
    @FXML
    private Button accountTabCancelButton;
    @FXML
    private TextField addNewAccountField;
    @FXML
    private Button addNewAccountButton;

    @FXML
    private Tab pricingTab;
    @FXML
    private Tab settingsTab;

    //Google Maps
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;

    //UI Controller Classes
    private BookingTabController btc = new BookingTabController();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Booking b = new BookingImpl(new AccountImpl("K-22"));
        mapView.addMapInializedListener(this);
        initializeLists();
        initializeBookingForm();
        initializeBookingTable();
        LocalDate d = bookingDateSelected.getValue();
    }

    private void initializeBookingForm(){
        bookingNumber.setText("#"+BookingImpl.bookingNumberCounter);
        bookingDateSelected.setValue(LocalDate.now());
        bookingTimeSelected.setText(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
        bookingAccountOrCash.setValue(Cash.getInstance());
        bookingVehicleType.setValue("Saloon");
        bookingPassengerNo.setValue("1");
        bookingFromNumberField.clear();
        bookingFromAddressField.clear();
        bookingToNumberField.clear();
        bookingToAddressField.clear();
        bookingPassengerNameField.clear();
        bookingPassengerTelField.clear();
        bookingPassengerEmailField.clear();
        bookingCommentsField.clear();
        bookingPriceField.clear();
    }

    private void initializeLists(){
        ObservableLists.initialise();
        accountTabList.setItems(ObservableLists.accountList);
        bookingAccountOrCash.setItems(ObservableLists.accountList);
        driverTabList.setItems(ObservableLists.driverList);
        bookingVehicleType.setItems(ObservableLists.vehicleTypeList);
    }

    @Override
    public void mapInitialized() {
        MapController mc = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel,bookingPriceField);
        mc.mapInitialized();
    }

    private void initializeBookingTable(){
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        pickUpCol.setCellValueFactory(new PropertyValueFactory<>("PickUpAddress"));
        dropOffCol.setCellValueFactory(new PropertyValueFactory<>("DropOffAddress"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("Comments"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("FormattedPrice"));
        bookingTableView.setItems(ObservableLists.bookingsList);
    }

    @FXML
    public void timeFieldValidation(KeyEvent event) {
        btc.timeFieldValidation(event);
    }

    @FXML
    public void calculateRouteButtonFired(){
        String origin = bookingFromNumberField.getPromptText()+" "+bookingFromAddressField.getText();
        String destination = bookingToNumberField.getPromptText()+" "+bookingToAddressField.getText();
        calculateRouteOnMap(origin,destination);
    }

    public void calculateRouteOnMap(String origin, String destination) {
        MapController mapControl = new MapController(mapView, map, journeyDistanceLabel, journeyDurationLabel, bookingPriceField);
        mapControl.newRoute(origin, destination);
        XmlParser x = new XmlParser();
        String journeyTime;
        try {
            journeyTime = x.getJourneyDuration(origin, destination);
        } catch (Exception e) {
            journeyTime = "Unavailable";
        }
        System.out.println(mapControl.getJourneyDistance()+"DISTANCE");
        journeyDurationLabel.setText("Duration: " + journeyTime);
        journeyPickupFromLabel.setText("Pickup From: " + bookingFromAddressField.getText());
        System.out.println(mapControl.getJourneyTime() +" DURATION TAKEN !");
    }

    public void saveBooking(){
        BookingImpl b = new BookingImpl(bookingAccountOrCash.getValue());
        b.setVehicleType(bookingVehicleType.getSelectionModel().getSelectedItem());
        b.setNoPassengers(bookingPassengerNo.getSelectionModel().getSelectedItem().toString());
        System.out.println("PASSENGERS: "+bookingPassengerNo.getSelectionModel().getSelectedItem().toString());
        b.setDate(bookingDateSelected.getValue());
        b.setTime(bookingTimeSelected.getText());
        if(bookingFromNumberField.getText().equals("")) {
            b.setPickUpAddress(bookingFromAddressField.getText());
        } else {
            b.setPickUpAddress(bookingFromNumberField.getText()+", "+bookingFromAddressField.getText());
        }
        if(bookingToNumberField.getText().equals("")) {
            b.setDropOffAddress(bookingToAddressField.getText());
        } else {
            b.setDropOffAddress(bookingToNumberField.getText()+", "+bookingToAddressField.getText());
        }

        b.setClientName(bookingPassengerNameField.getText());
        b.setClientTel(bookingPassengerTelField.getText());
        b.setClientEmail(bookingPassengerEmailField.getText());
        b.setComments(bookingCommentsField.getText());
        b.setPrice(Double.parseDouble(bookingPriceField.getText()));
        initializeBookingForm();
    }

    @FXML
    public void addressKeyPressed(KeyEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        btc.addressKeyPressed(event);
    }

    @FXML
    public void bookingAcceptButtonFired(){
        saveBooking();
    }

    @FXML
    public void bookingCancelButtonFired(){
        initializeBookingForm();
    }




}



