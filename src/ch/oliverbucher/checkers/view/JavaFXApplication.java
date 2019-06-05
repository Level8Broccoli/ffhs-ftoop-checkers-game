package ch.oliverbucher.checkers.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class JavaFXApplication extends Application implements ApplicationInterface {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        String pathToConfig = "ch.oliverbucher.checkers.resources.";
        ResourceBundle resources = ResourceBundle.getBundle(pathToConfig + "config");

        Parent root = FXMLLoader.load(getClass().getResource("JavaFXApplication.fxml"), resources);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getScene().getStylesheets().add(this.getClass().getResource("JavaFXApplicationStyles.css").toExternalForm());
        stage.setTitle(resources.getString("GAME_TITLE"));
        stage.show();
    }
}
