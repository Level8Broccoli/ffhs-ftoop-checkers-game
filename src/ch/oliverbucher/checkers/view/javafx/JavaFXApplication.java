package ch.oliverbucher.checkers.view.javafx;

import ch.oliverbucher.checkers.controller.javafx.JavaFXGameController;
import ch.oliverbucher.checkers.controller.javafx.JavaFXLaunchController;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.ApplicationInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class JavaFXApplication extends Application implements ApplicationInterface {
    
    @Override
    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Load controllers
        JavaFXLaunchController launchController = new JavaFXLaunchController();
        JavaFXGameController gameController = new JavaFXGameController();

        // Load stylesheet
        String stylesheetFile = this.getClass().getResource("JavaFXApplicationStyles.css").toExternalForm();

        // Launch Scene
        URL launchFxmlURL = getClass().getResource("JavaFXApplicationLaunchScene.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        launchFxmlLoader.setController(launchController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheetFile);
        Scene launchScene = new Scene(launchRoot);

        // Game Scene
        URL fxmlURLGame = getClass().getResource("JavaFXApplicationGameScene.fxml");
        FXMLLoader fxmlLoaderGame = new FXMLLoader(fxmlURLGame, Config.getResourceBundle());
        fxmlLoaderGame.setController(gameController);
        Parent gameRoot = fxmlLoaderGame.load();
        gameRoot.getStylesheets().add(stylesheetFile);
        Scene gameScene = new Scene(gameRoot);

        // give controller control over scene changes
        launchController.setGameScene(gameScene);
        gameController.setLaunchScene(launchScene);

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(Config.getValue("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(Config.getValue("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(Config.getValue("WINDOW_HEIGHT")));

        stage.show();
    }
}