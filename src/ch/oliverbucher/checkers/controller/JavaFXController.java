package ch.oliverbucher.checkers.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXController implements Initializable {

    @FXML
    private ToggleButton btnHuman;

    @FXML
    private ToggleButton btnComputer;

    @FXML
    private Button btnStartGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStartGame.setOnAction(this::onClick);
    }

    private void onClick(ActionEvent event) {
        System.out.println("click is working!");
        System.out.println("Human: " + btnHuman.isSelected());
        System.out.println("Computer: " + btnComputer.isSelected());
    }
}
