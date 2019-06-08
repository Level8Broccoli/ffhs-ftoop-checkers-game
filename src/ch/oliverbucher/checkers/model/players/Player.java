package ch.oliverbucher.checkers.model.players;

import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;

public class Player {

  private final PlayerColor playerColor;
  private PlayerType playerType;

  public Player(PlayerType playerType, PlayerColor playerColor) {

    this.playerType = playerType;
    this.playerColor = playerColor;
  }

  public void setPlayerType(PlayerType playerType) {
    this.playerType = playerType;
  }

  public PlayerColor getPlayerColor() {

    return playerColor;
  }

  public boolean isDirectionOfPlayUp() {

    return playerColor == PlayerColor.WHITE;
  }
}
