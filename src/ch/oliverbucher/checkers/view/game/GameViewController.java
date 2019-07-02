package ch.oliverbucher.checkers.view.game;

import ch.oliverbucher.checkers.CheckersGamePresenter;
import ch.oliverbucher.checkers.resources.Config;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class GameViewController implements Initializable {

  @FXML private Button btnRestartGame;

  @FXML private Button btnRules;

  private CheckersGamePresenter presenter;

  public void initialize(URL location, ResourceBundle resources) {

    btnRestartGame.setOnAction(this::onClickNewGame);
    btnRules.setOnAction(this::onClickShowRules);
  }

  private void onClickShowRules(ActionEvent event) {

    try {
      if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(new URI(Config.LINK_RULES));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void onClickNewGame(ActionEvent event) {

    presenter.restartGame();
  }

  public void setPresenter(CheckersGamePresenter presenter) {

    this.presenter = presenter;
  }
}
