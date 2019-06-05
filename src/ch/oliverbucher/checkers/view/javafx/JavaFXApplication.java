package ch.oliverbucher.checkers.view.javafx;

import ch.oliverbucher.checkers.controller.javafx.JavaFXController;
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
    public void startGame() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Load controllers
        JavaFXController javaFXController = new JavaFXController();

        // Load stylesheet
        String stylesheetFile = this.getClass().getResource("JavaFXApplicationStyles.css").toExternalForm();

        // Launch Scene
        URL launchFxmlURL = getClass().getResource("JavaFXApplicationLaunchScene.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        launchFxmlLoader.setController(javaFXController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheetFile);
        Scene launchScene = new Scene(launchRoot);

        // Game Scene
        URL fxmlURLGame = getClass().getResource("JavaFXApplicationGameScene.fxml");
        FXMLLoader fxmlLoaderGame = new FXMLLoader(fxmlURLGame, Config.getResourceBundle());
        fxmlLoaderGame.setController(javaFXController);
        Parent gameRoot = fxmlLoaderGame.load();
        gameRoot.getStylesheets().add(stylesheetFile);
        Scene gameScene = new Scene(gameRoot);

        // give controller control over scene changes
        javaFXController.setGameScene(gameScene);
        javaFXController.setLaunchScene(launchScene);
        javaFXController.setStage(stage);

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(Config.getValue("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(Config.getValue("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(Config.getValue("WINDOW_HEIGHT")));

        stage.show();
    }

//    public void drawBoard(GridPane container) {
//
//        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
//        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));
//
//        Board board = new Board(boardWidth, boardHeight);
//
//        for (int i = 0; i < board.getSumOfSpaces(); i++) {
//            BoardSpace currentSpace = board.getBoardSpaces()[i];
//
//            int currentPositionX = currentSpace.getPosition().getPositionX();
//            int currentPositionY = currentSpace.getPosition().getPositionY();
//            BoardColor currentBoardColor = currentSpace.getBoardColor();
//
//            Label label = new Label("Text");
//            label.setId(String.valueOf(currentBoardColor));
//
//            container.add(label, currentPositionX, currentPositionY);
//        }
//    }
}