package ch.oliverbucher.checkers.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class Controller {

    @FXML
    public ToggleButton btnHuman;
    public ToggleButton btnComputer;

    public void onClick(ActionEvent event) {
        System.out.println("click is working!");
        System.out.println("Human: " + btnHuman.isSelected());
        System.out.println("Computer: " + btnComputer.isSelected());
    }
}
