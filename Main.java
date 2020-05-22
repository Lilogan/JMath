import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("JMath");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}