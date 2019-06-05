package ch.oliverbucher.checkers.controller.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXController implements Initializable {

    @FXML
    private BorderPane container;

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnStartGame;

    private Scene launchScene;
    private Scene gameScene;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (btnNewGame != null) {
            btnNewGame.setOnAction(this::onClickNewGame);
        }

        if (btnStartGame != null) {
            btnStartGame.setOnAction(this::onClickStartGame);
        }
    }

    private void onClickNewGame(ActionEvent event) {

        newGame();
    }

    private void newGame() {

        stage.setScene(launchScene);
    }

    private void onClickStartGame(ActionEvent event) {

        startGame();
    }

    private void startGame() {

        stage.setScene(gameScene);
    }

    public void setGameScene(Scene gameScene) {

        this.gameScene = gameScene;
    }

    public void setLaunchScene(Scene launchScene) {

        this.launchScene = launchScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
