package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import java.util.Map;

public abstract class Token {

  public final Player playerOwner;

  public Token(Player playerOwner) {
    this.playerOwner = playerOwner;
  }

  public final String getName() {
    return playerOwner.getPlayerColor().name();
  }

  public abstract Map<PositionXY, HorizontalDirection> getPossibleMoves(PositionXY currentPosition);

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
