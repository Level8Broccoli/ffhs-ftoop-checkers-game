package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import java.util.HashMap;

public abstract class Token {

  final Player playerOwner;

  Token(Player playerOwner) {
    this.playerOwner = playerOwner;
  }

  public final String getName() {
    return playerOwner.getPlayerColor().name();
  }

  public abstract HashMap<PositionXY, HorizontalDirection> getPossibleMoves(
      PositionXY currentPosition);

  public Player getPlayerOwner() {

    return playerOwner;
  }

  public PositionXY getPositionBehindOpponent(
      PositionXY jumpOverPosition, HorizontalDirection direction) {

    int targetPositionX = jumpOverPosition.positionX;
    int targetPositionY = jumpOverPosition.positionY;

    if (direction == HorizontalDirection.LEFT) {
      targetPositionX--;
    } else {
      targetPositionX++;
    }

    if (playerOwner.getDirectionOfPlay().isUp()) {
      targetPositionY--;
    } else {
      targetPositionY++;
    }

    return Positions.getPosition(targetPositionX, targetPositionY);
  }
}
