package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.enumaration.BoardColor;
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

        URL launchFxmlURL = getClass().getResource("view/launch/LaunchView.fxml");
        FXMLLoader launchFxmlLoader = new FXMLLoader(launchFxmlURL, Config.getResourceBundle());
        LaunchViewController launchViewController = new LaunchViewController();
        launchViewController.setPresenter(this);
        launchFxmlLoader.setController(launchViewController);
        Parent launchRoot = launchFxmlLoader.load();
        launchRoot.getStylesheets().add(stylesheet);
        launchScene = new Scene(launchRoot);
    }

    private void setUpGameScreen() throws Exception {

        URL gameFxmlURL = getClass().getResource("view/game/GameView.fxml");
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

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                PositionXY currentPosition = Positions.getPosition(x, y);

                StackPane stackPane = new StackPane();
                stackPane.setMaxSize(Config.LENGHT_OF_SPACE, Config.LENGHT_OF_SPACE);

                // draw board layer
                BoardSpace currentSpace = model.getBoardLayer().get(currentPosition);
                BoardColor currentBoardColor = currentSpace.getBoardColor();

                Button btnBackground = new Button();
                btnBackground.setId(String.valueOf(currentBoardColor));
                stackPane.getChildren().add(btnBackground);

                // draw token layer
                if (model.getTokenLayer().get(currentPosition) != null) {
                    PlayerToken currentPlayerToken = model.getTokenLayer().get(currentPosition);
                    Button btnToken = new Button();
                    btnToken.setId(currentPlayerToken.getName());
                    stackPane.getChildren().add(btnToken);
                }

                // draw marked layer
//                if (currentPlayerToken.hasPossibleMoves()) {
//                    Button btnMark = new Button();
//                    btnMark.setId("btnMarked");
//                    stackPane.getChildren().add(btnMark);
//                }

                // draw interaction layer
                Button btnInteraction = new Button();
                int positionX = x;
                int positionY = y;
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