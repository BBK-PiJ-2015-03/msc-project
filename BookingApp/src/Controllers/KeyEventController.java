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

    public Date currentTimePlusFiveMinutes() {
        Date d = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 5);
        return cal.getTime();
    }

    public void bookingTable(KeyEvent event, TableView table) {
        if(event.getCode() == KeyCode.ENTER){
            try {
                AlertDispatch.booking = (BookingImpl) table.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/AlertDialog_css/AlertDialog_css.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
