package gui.loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class ViewProject extends Application {

    public static Locale locale = new Locale("ENG");
    public static ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Sample.fxml"), ResourceBundle.getBundle("lang", locale));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("gameName"));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
