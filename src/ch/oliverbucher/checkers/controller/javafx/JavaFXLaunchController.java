package ch.oliverbucher.checkers.controller.javafx;

import ch.oliverbucher.checkers.controller.LaunchControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXLaunchController implements Initializable, LaunchControllerInterface {

    @FXML
    private BorderPane container;

    @FXML
    private ToggleButton btnHuman;

    @FXML
    private ToggleButton btnComputer;

    @FXML
    private Button btnStartGame;

    private Scene gameScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnStartGame.setOnAction(this::onClick);
    }

    private void onClick(ActionEvent event) {

        startGame();
    }

    public void startGame() {

        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(gameScene);
    }

    public void setGameScene(Scene gameScene) {

        this.gameScene = gameScene;
    }
}
