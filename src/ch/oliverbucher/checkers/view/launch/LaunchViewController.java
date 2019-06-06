package ch.oliverbucher.checkers.view.launch;

import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.CheckersGamePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class LaunchViewController {

    private CheckersGamePresenter presenter;

    @FXML
    private ToggleButton btnHuman;

    @FXML
    private ToggleButton btnComputer;

    @FXML
    private Button btnStartGame;

    @FXML
    private Label lblMessage;

    public void initialize() {

        btnStartGame.setOnAction(this::onClickStartGame);
        btnHuman.setOnAction(this::onClickOpponentChosen);
        btnComputer.setOnAction(this::onClickOpponentChosen);
    }

    private void onClickOpponentChosen(ActionEvent event) {
        if (lblMessage.getText() != null) {
            lblMessage.setText("");
        }
        btnStartGame.setDefaultButton(true);

    }

    private void onClickStartGame(ActionEvent event) {

        if (!btnHuman.isSelected() && !btnComputer.isSelected()) {
            lblMessage.setText(Config.getValue("MSG_NO_OPPONENT"));
        } else if (btnHuman.isSelected()) {
            presenter.setOpponent(PlayerType.HUMAN);
            presenter.startGame();
        } else if (btnComputer.isSelected()) {
            presenter.setOpponent(PlayerType.COMPUTER);
            presenter.startGame();
        }
    }

    public void setPresenter(CheckersGamePresenter presenter) {

        this.presenter = presenter;
    }

}
