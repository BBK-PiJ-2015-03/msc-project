import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainLayoutGMaps.fxml"));
        window.setTitle("Desktop Booking & Dispatch System");
        window.setMinWidth(1024);
        window.setMinHeight(720);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("CSS/future-booking-highlighting.css");
        window.setScene(scene);
        window.show();
    }
}
