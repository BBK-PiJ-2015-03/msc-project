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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainLayout.fxml"));
        window.setTitle("Desktop Booking & Dispatch System");
        window.setMinWidth(1024);
        window.setMinHeight(720);
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }
}
