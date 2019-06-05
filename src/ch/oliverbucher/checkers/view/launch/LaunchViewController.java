package ch.oliverbucher.checkers.view.launch;

import ch.oliverbucher.checkers.view.CheckersGamePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LaunchViewController {

    private CheckersGamePresenter presenter;

    @FXML
    private Button btnStartGame;

    public void initialize() {
        btnStartGame.setOnAction(this::onClickStartGame);
    }

    private void onClickStartGame(ActionEvent event) {
        System.out.println("Start Game");
    }

    public void setPresenter(CheckersGamePresenter presenter) {
        this.presenter = presenter;
    }

}
