package ch.oliverbucher.checkers.controller.javafx;

import ch.oliverbucher.checkers.model.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXLaunchController implements Initializable {

    @FXML
    private BorderPane container;

    @FXML
    private ToggleButton btnHuman;

    @FXML
    private ToggleButton btnComputer;

    @FXML
    private Button btnStartGame;

    private Scene gameScene;
    private Scene launchScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStartGame.setOnAction(this::onClick);
    }

    private void onClick(ActionEvent event) {
        System.out.println("click is working!");
        System.out.println("Human: " + btnHuman.isSelected());
        System.out.println("Computer: " + btnComputer.isSelected());

        startGame(event);
    }

    private void startGame(ActionEvent event) {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(gameScene);

        new Board(12,12);

    }

    public void setLaunchScene(Scene launchScene) {
        this.launchScene = launchScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
}
