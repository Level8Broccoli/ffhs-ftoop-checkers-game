package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.Direction;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import java.util.Map;

public abstract class Token {

  public final Player playerOwner;

  public Token(Player playerOwner) {
    this.playerOwner = playerOwner;
  }

  public String getName() {
    return playerOwner.getPlayerColor().name();
  }

  public abstract Map<PositionXY, Direction> getPossibleMoves(PositionXY currentPosition);

  public Player getPlayerOwner() {

    return playerOwner;
  }

  public PositionXY getNextPositionInDirection(PositionXY currentPosition, Direction direction) {

    int targetPositionX = currentPosition.positionX;
    int targetPositionY = currentPosition.positionY;

    switch (direction) {
      case LEFT_UP:
        targetPositionX--;
        targetPositionY--;
        break;
      case RIGHT_UP:
        targetPositionX++;
        targetPositionY--;
        break;
      case LEFT_DOWN:
        targetPositionX--;
        targetPositionY++;
        break;
      case RIGHT_DOWN:
        targetPositionX++;
        targetPositionY++;
        break;
    }

    PositionXY nextPosition = new PositionXY(targetPositionX, targetPositionY);

    if (!nextPosition.isNotOutsideTheBoard()) {
      return null;
    }

    return nextPosition;
  }
}
