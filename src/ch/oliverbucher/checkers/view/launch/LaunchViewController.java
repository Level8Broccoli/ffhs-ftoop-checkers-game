package ch.oliverbucher.checkers.view.launch;

import ch.oliverbucher.checkers.CheckersGamePresenter;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.resources.Config;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class LaunchViewController implements Initializable {

  private CheckersGamePresenter presenter;

  @FXML private ToggleButton btnHuman;

  @FXML private ToggleButton btnComputer;

  @FXML private Button btnStartGame;

  @FXML private Label lblMessage;

  public void initialize(URL location, ResourceBundle resources) {

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
      lblMessage.setText(Config.MSG_NO_OPPONENT);
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
