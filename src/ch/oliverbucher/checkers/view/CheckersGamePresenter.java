package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.BoardSpace;
import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.game.GameViewController;
import ch.oliverbucher.checkers.view.launch.LaunchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;

public class CheckersGamePresenter extends Application {

    private CheckersGameModel model;

    private String stylesheet;

    private Scene gameScene;
    private Scene launchScene;
    private Stage stage;

    public CheckersGamePresenter() {

        model = new CheckersGameModel();
    }

    public void startApplication() {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        // Load stylesheet
        stylesheet = this.getClass().getResource("/ch/oliverbucher/checkers/resources/JavaFXApplicationStyles.css").toExternalForm();

        setUpLaunchScreen();
        setUpGameScreen();

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(Config.getValue("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(Config.getValue("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(Config.getValue("WINDOW_HEIGHT")));

        stage.show();
    }

    private void setUpLaunchScreen() throws Exception {

        URL launchFxmlURL = getClass().getResource("launch/LaunchView.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        LaunchViewController launchViewController = new LaunchViewController();
        launchViewController.setPresenter(this);
        launchFxmlLoader.setController(launchViewController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheet);
        launchScene = new Scene(launchRoot);
    }

    private void setUpGameScreen() throws Exception {

        URL gameFxmlURL = getClass().getResource("game/GameView.fxml");
        FXMLLoader gameFxmlLoader = new FXMLLoader(gameFxmlURL, Config.getResourceBundle());
        GameViewController gameViewController = new GameViewController();
        gameViewController.setPresenter(this);
        gameFxmlLoader.setController(gameViewController);
        Parent gameRoot = gameFxmlLoader.load();
        gameRoot.getStylesheets().add(stylesheet);
        gameScene = new Scene(gameRoot);
    }

    public void startGame() {

        stage.setScene(gameScene);
        drawBoard();
    }

    public void newGame() {

        stage.setScene(launchScene);
    }

    public void drawBoard() {

        GridPane gridContainer = (GridPane) gameScene.lookup("#boardContainer");

        Board board = model.getBoard();
        for (int i = 0; i < board.getSumOfSpaces(); i++) {
            BoardSpace currentSpace = board.getBoardSpaces()[i];

            int currentPositionX = currentSpace.getPosition().getPositionX();
            int currentPositionY = currentSpace.getPosition().getPositionY();
            BoardColor currentBoardColor = currentSpace.getBoardColor();

            Label label = new Label("Text");
            label.setId(String.valueOf(currentBoardColor));

            gridContainer.add(label, currentPositionX, currentPositionY);
        }
    }

    public void setOpponent(PlayerType human) {
        model.setOpponent(human);
    }
}