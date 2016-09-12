package Controllers;

import Model.BookingImpl;
import Utils.TimeFieldValidator;
import Utils.XmlParser;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Methods for booking tab will be passed through here
 */
public class KeyEventController {

    /**
     * Start address lookup, when 'Enter' is pressed this will calculate the full address
     * and replace the partial address/postcode in the given field.
     * Could later improve this method with a drop down of all possible results, on each keypress update the drop down.
     * @param event
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws SAXException
     * @throws IOException
     */
    void addressKeyPressed(KeyEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            Service<Void> backgroundThread = new Service<Void>() {
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
                            }
                            return null;
                        }
                    };
                }
            };
            backgroundThread.restart();
        }
    }

    /**
     * Check that time field has valid format eg 'HH:MM' and conforms to a 24 hour clock.
     * @param event
     */
    public void timeFieldValidation(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TimeFieldValidator tfv = new TimeFieldValidator();
            String time = tfv.format(((TextField) event.getSource()).getText());
            if (tfv.validate(time)) {
                ((TextField) event.getSource()).setText(time);
            } else {
                ((TextField) event.getSource()).setText(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
            }
        }
    }

    /**
     * Calculates the systems current time and adds 5 minutes onto it
     * this is used when initialising the booking form.
     * @return current time + 5 minutes
     */
    public Date currentTimePlusFiveMinutes() {
        Date d = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 5);
        return cal.getTime();
    }

    /**
     * Provides a popup dialogue to allow the dispatch of highlighted job to a driver,
     * when a job is highlighted and the 'Enter' key is pressed this will allow you to enter a
     * driver ID to dispatch a job to them.
     * @param event 'Enter' button
     * @param table of bookings awaiting dispatch
     */
    public void bookingTable(KeyEvent event, TableView table) {
        if(event.getCode() == KeyCode.ENTER){
            try {
                AlertDispatch.booking = (BookingImpl) table.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/AlertDialog_css/AlertDialog_css.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                AlertDispatch.stage = stage;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
