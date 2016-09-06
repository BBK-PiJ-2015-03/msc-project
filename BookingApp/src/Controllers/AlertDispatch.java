package Controllers;

import Model.BookingImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AlertDispatch{

    public static BookingImpl booking;

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

    @FXML
    void dispatchJob(ActionEvent event) {
        BookingListener.addBooking(booking, selectedDriver.getText());
    }

}
