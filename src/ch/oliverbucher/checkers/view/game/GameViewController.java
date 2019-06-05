package ch.oliverbucher.checkers.view.game;

import ch.oliverbucher.checkers.view.CheckersGamePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameViewController {

    @FXML
    private Button btnNewGame;
    private CheckersGamePresenter presenter;

    public void initialize() {
        btnNewGame.setOnAction(this::onClickNewGame);
    }

    private void onClickNewGame(ActionEvent event) {
        System.out.println("New Game");
    }

    public void setPresenter(CheckersGamePresenter presenter) {
        this.presenter = presenter;
    }

}
