package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.config.Config;
import ch.oliverbucher.checkers.config.ConfigJavaFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication extends Application implements ApplicationInterface {

    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("JavaFXApplication.fxml"));

        Scene scene = new Scene(root, ConfigJavaFX.windowWidth, ConfigJavaFX.windowHeight);
        stage.setScene(scene);
        stage.setTitle(Config.gameName);
        stage.show();
    }
}
