package ch.oliverbucher.checkers.view.javafx;

import ch.oliverbucher.checkers.controller.javafx.JavaFXController;
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

        // Load controller
        JavaFXController controller = new JavaFXController();

        // Load stylesheet
        String stylesheetFile = this.getClass().getResource("JavaFXApplicationStyles.css").toExternalForm();

        // Launch Scene
        URL launchFxmlURL = getClass().getResource("JavaFXApplicationLaunchScene.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, resources);
        launchFxmlLoader.setController(controller);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheetFile);
        Scene launchScene = new Scene(launchRoot);

        // Game Scene
        URL fxmlURLGame = getClass().getResource("JavaFXApplicationGameScene.fxml");
        FXMLLoader fxmlLoaderGame = new FXMLLoader(fxmlURLGame, resources);
        fxmlLoaderGame.setController(controller);
        Parent gameRoot = fxmlLoaderGame.load();
        gameRoot.getStylesheets().add(stylesheetFile);
        Scene gameScene = new Scene(gameRoot);

        controller.setGameScene(gameScene);
        controller.setLaunchScene(launchScene);

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(resources.getString("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(resources.getString("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(resources.getString("WINDOW_HEIGHT")));

        stage.show();
    }
}