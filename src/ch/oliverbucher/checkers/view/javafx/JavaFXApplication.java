package ch.oliverbucher.checkers.view.javafx;

import ch.oliverbucher.checkers.controller.JavaFXController;
import ch.oliverbucher.checkers.view.ApplicationInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXApplication extends Application implements ApplicationInterface {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Load config file
        String pathToConfig = "ch.oliverbucher.checkers.resources.";
        ResourceBundle resources = ResourceBundle.getBundle(pathToConfig + "config");

        // Load fxml file and controller
        URL fxmlURL = getClass().getResource("JavaFXApplication.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL, resources);
        fxmlLoader.setController(new JavaFXController());

        Parent root = fxmlLoader.load();

        // Load styles
        root.getStylesheets().add(this.getClass().getResource("JavaFXApplicationStyles.css").toExternalForm());

        // Set scene and start application
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(resources.getString("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(resources.getString("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(resources.getString("WINDOW_HEIGHT")));

        stage.show();
    }
}
