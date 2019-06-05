package ch.oliverbucher.checkers.view;

import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.game.GameViewController;
import ch.oliverbucher.checkers.view.launch.LaunchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class CheckersGamePresenter extends Application {

    private CheckersGameModel model;

    private Scene gameScene;
    private Scene launchScene;

    private Stage stage;

    public CheckersGamePresenter() {

        // empty constructor is needed for javafx.application.Application to work properly
    }

    public CheckersGamePresenter(CheckersGameModel model) {

        this.model = model;
    }

    public void startApplication() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        // Load stylesheet
        String stylesheetFile =
                this.getClass().getResource("/ch/oliverbucher/checkers/resources/JavaFXApplicationStyles.css").toExternalForm();

        // Launch Screen
        URL launchFxmlURL = getClass().getResource("launch/LaunchView.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        LaunchViewController launchViewController = new LaunchViewController();
        launchViewController.setPresenter(this);
        launchFxmlLoader.setController(launchViewController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheetFile);
        launchScene = new Scene(launchRoot);

        // Game Screen
        URL gameFxmlURL = getClass().getResource("game/GameView.fxml");
        FXMLLoader gameFxmlLoader = new FXMLLoader(gameFxmlURL, Config.getResourceBundle());
        GameViewController gameViewController = new GameViewController();
        gameViewController.setPresenter(this);
        launchFxmlLoader.setController(launchViewController);
        Parent gameRoot = gameFxmlLoader.load();
        gameRoot.getStylesheets().add(stylesheetFile);
        gameScene = new Scene(gameRoot);

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(Config.getValue("GAME_TITLE"));
        stage.setWidth(Double.parseDouble(Config.getValue("WINDOW_WIDTH")));
        stage.setHeight(Double.parseDouble(Config.getValue("WINDOW_HEIGHT")));

        stage.show();
    }

    public void startGame() {
        stage.setScene(gameScene);
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
}