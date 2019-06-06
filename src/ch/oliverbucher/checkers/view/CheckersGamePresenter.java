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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

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

        ArrayList<ArrayList<BoardSpace>> board = model.getBoard();
        for (int i = 0; i < board.size(); i++) {
            ArrayList<BoardSpace> currentRow = board.get(i);

            for (int j = 0; j < currentRow.size(); j++ ){
                BoardSpace currentSpace = currentRow.get(j);

                BoardColor currentBoardColor = currentSpace.getBoardColor();

                StackPane stackPane = new StackPane();
                stackPane.setMaxSize(Config.LENGHT_OF_SPACE,Config.LENGHT_OF_SPACE);

                Button btnBackground = new Button();
                btnBackground.setId(String.valueOf(currentBoardColor));
                stackPane.getChildren().add(btnBackground);

                Button btnToken = new Button();
                btnToken.setId("token");
                stackPane.getChildren().add(btnToken);
//
//            Button btnMark = new Button();
//            btnMark.setId("mark");
//            stackPane.getChildren().add(btnMark);
//
//            Button btnInteraction = new Button();
//            btnInteraction.setId("hide");
//            btnInteraction.setId(currentPositionX + "-" + currentPositionY);
//            stackPane.getChildren().add(btnInteraction);

                gridContainer.add(stackPane, i, j);
            }
        }
    }

    public void setOpponent(PlayerType human) {
        model.setOpponent(human);
    }
}