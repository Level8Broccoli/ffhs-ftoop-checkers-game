package ch.oliverbucher.checkers.view.game;

import ch.oliverbucher.checkers.CheckersGamePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnTest;

    @FXML
    private Button hide;

    private CheckersGamePresenter presenter;

    public void initialize(URL location, ResourceBundle resources) {

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
