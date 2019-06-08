package ch.oliverbucher.checkers.view.game;

import ch.oliverbucher.checkers.CheckersGamePresenter;
import ch.oliverbucher.checkers.resources.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnRules;

    @FXML
    private Button hide;

    private CheckersGamePresenter presenter;

    public void initialize(URL location, ResourceBundle resources) {

        btnNewGame.setOnAction(this::onClickNewGame);
        btnRules.setOnAction(this::onClickShowRules);
    }

    private void onClickShowRules(ActionEvent event) {

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(Config.LINK_RULES));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onClickNewGame(ActionEvent event) {

        presenter.newGame();
    }

    public void setPresenter(CheckersGamePresenter presenter) {

        this.presenter = presenter;
    }
}