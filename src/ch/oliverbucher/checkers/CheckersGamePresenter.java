package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.layer.space.BoardSpace;
import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.game.GameViewController;
import ch.oliverbucher.checkers.view.launch.LaunchViewController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class CheckersGamePresenter extends Application {

    private CheckersGameModel model;

    private String stylesheet;

    private Scene gameScene;
    private Scene launchScene;
    private Stage stage;

    public void startApplication() {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        TODO
        model = new CheckersGameModel();

        stage = primaryStage;

        // Load stylesheet
        stylesheet = this.getClass().getResource("/ch/oliverbucher/checkers/resources/JavaFXApplicationStyles.css").toExternalForm();

        setUpLaunchScreen();
        setUpGameScreen();

        // Set scene and start application
        stage.setScene(launchScene);
        stage.setTitle(Config.GAME_TITLE);
        stage.setWidth(Config.WINDOW_WIDTH);
        stage.setHeight(Config.WINDOW_HEIGHT);

        stage.show();
    }

    private void setUpLaunchScreen() throws Exception {

        URL launchFxmlURL = getClass().getResource("view/launch/LaunchView.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.RESOURCE_BUNDLE);
        LaunchViewController launchViewController = new LaunchViewController();
        launchViewController.setPresenter(this);
        launchFxmlLoader.setController(launchViewController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheet);
        launchScene = new Scene(launchRoot);
    }

    private void setUpGameScreen() throws Exception {

        URL gameFxmlURL = getClass().getResource("view/game/GameView.fxml");
        FXMLLoader gameFxmlLoader = new FXMLLoader(gameFxmlURL, Config.RESOURCE_BUNDLE);
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
        model = new CheckersGameModel();
        stage.setScene(launchScene);
    }

    public void drawBoard() {

        GridPane gridContainer = (GridPane) gameScene.lookup("#boardContainer");
        gridContainer.getChildren().removeAll();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                PositionXY currentPosition = Positions.getPosition(x, y);

                StackPane stackPane = new StackPane();
                stackPane.setMaxSize(Config.LENGTH_OF_SPACE, Config.LENGTH_OF_SPACE);

                // draw board layer
                final BoardSpace currentSpace = model.getBoardLayer().get(currentPosition);
                final BoardColor currentBoardColor = currentSpace.getBoardColor();

                final Button btnBackground = new Button();
                btnBackground.setId(String.valueOf(currentBoardColor));
                stackPane.getChildren().add(btnBackground);

                // draw token layer
                final PlayerToken currentPlayerToken = model.getTokenLayer().getTokenAt(currentPosition);
                if (currentPlayerToken != null) {
                    Button btnToken = new Button();
                    btnToken.setId(currentPlayerToken.getName());
                    stackPane.getChildren().add(btnToken);
                }

                // draw marked layer
                final MarkType currentMarkType = model.getMarkLayer().get(currentPosition);
                if (currentMarkType != null) {
                    Button btnMark = new Button();
                    btnMark.setId(currentMarkType.getName());
                    stackPane.getChildren().add(btnMark);
                }

                // draw interaction layer
                Button btnInteraction = new Button();
                final int positionX = x;
                final int positionY = y;
                btnInteraction.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        (EventHandler<Event>) event -> {
                            model.clickEvent(positionX, positionY);
                            drawBoard();
                        });
                btnInteraction.setId("btnInteraction");
                stackPane.getChildren().add(btnInteraction);

                gridContainer.add(stackPane, x, y);
            }
        }
    }

    public void setOpponent(PlayerType playerType) {
        model.setOpponent(playerType);
    }
}