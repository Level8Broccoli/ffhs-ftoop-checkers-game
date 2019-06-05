package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.resources.Config;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class CheckersGameView extends Application {

    private CheckersGameModel model;
    private Scene gameScene;
    private Scene launchScene;

    @FXML
    private BorderPane container;

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnStartGame;

    public CheckersGameView() {

    }

    public CheckersGameView(CheckersGameModel model) {

        this.model = model;
    }

    public void startGame() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load stylesheet
        String stylesheetFile = this.getClass().getResource("javafx/JavaFXApplicationStyles.css").toExternalForm();

        // Launch Screen
        URL launchFxmlURL = getClass().getResource("javafx/JavaFXApplicationLaunchScene.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheetFile);
        launchScene = new Scene(launchRoot);

        // Game Screen
        URL gameFxmlURL = getClass().getResource("javafx/JavaFXApplicationGameScene.fxml");
        FXMLLoader gameFxmlLoader = new FXMLLoader(gameFxmlURL, Config.getResourceBundle());
        Parent gameRoot = gameFxmlLoader.load();
        gameRoot.getStylesheets().add(stylesheetFile);
        gameScene = new Scene(gameRoot);

        // Set scene and start application
        primaryStage.setScene(launchScene);
        primaryStage.setTitle(Config.getValue("GAME_TITLE"));
        primaryStage.setWidth(Double.parseDouble(Config.getValue("WINDOW_WIDTH")));
        primaryStage.setHeight(Double.parseDouble(Config.getValue("WINDOW_HEIGHT")));

        primaryStage.show();
    }

//    public void drawBoard() {
//
//        Board board = model.getBoard();
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
//            GridPane container = (GridPane) gameScene.lookup("#boardContainer");
//            container.add(label, currentPositionX, currentPositionY);
//        }
//    }

    public Scene getGameScene() {

        return gameScene;
    }

    public Scene getLaunchScene() {

        return launchScene;
    }

    public Stage getStage() {

        return (Stage) container.getScene().getWindow();
    }

    public Button getBtnNewGame() {

        return btnNewGame;
    }

    public Button getBtnStartGame() {

        return btnStartGame;
    }
}