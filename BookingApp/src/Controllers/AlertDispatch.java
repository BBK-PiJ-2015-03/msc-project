package Controllers;

import Model.BookingImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlertDispatch{

    public static BookingImpl booking;
    public static Stage stage;

    @FXML
    private TextField selectedDriver;

    @FXML
    private Label detailsLabel;

    @FXML
    private HBox actionParent;

    @FXML
    private Button cancelButton;

    @FXML
    private Button sendButton;

    /**
     * Dispatches job to driver
     * @param event
     */
    @FXML
    void dispatchJob(ActionEvent event) {
        BookingListener.addBooking(booking, selectedDriver.getText());
//        ObservableLists.bookingsList.remove(booking);
//        Removing booking from Awaiting_Dispatch key in database, should update locally aswell
        stage.close();
    }

}
