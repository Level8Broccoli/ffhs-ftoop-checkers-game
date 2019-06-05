package ch.oliverbucher.checkers.view.game;

import ch.oliverbucher.checkers.view.CheckersGamePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameViewController {

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnTest;

    @FXML
    private Button hide;

    private CheckersGamePresenter presenter;

    public void initialize() {

        btnNewGame.setOnAction(this::onClickNewGame);
        btnTest.setOnAction(this::onClickTest);
    }

    private void onClickTest(ActionEvent event) {
    }

    private void onClickNewGame(ActionEvent event) {

        presenter.newGame();
    }

    public void setPresenter(CheckersGamePresenter presenter) {

        this.presenter = presenter;
    }

}
