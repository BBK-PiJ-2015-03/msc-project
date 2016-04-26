import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        //launch(args);
        System.out.println("Hello");
    }

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        window.setTitle("Desktop Booking & Dispatch System");
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }
}
