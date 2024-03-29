package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.Direction;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import java.util.HashMap;
import java.util.Map;

public class StandardToken extends Token {

  public StandardToken(Player playerOwner) {
    super(playerOwner);
  }

  @Override
  public Map<PositionXY, Direction> getPossibleMoves(PositionXY currentPosition) {

    Map<PositionXY, Direction> possibleMoves = new HashMap<>();

    final int currentPositionX = currentPosition.positionX;
    final int currentPositionY = currentPosition.positionY;

    final boolean directionOfPlayIsUp = playerOwner.isDirectionOfPlayUp();
    final int nextRowY = directionOfPlayIsUp ? currentPositionY - 1 : currentPositionY + 1;

    final PositionXY possibleMoveLeft = new PositionXY(currentPositionX - 1, nextRowY);
    if (possibleMoveLeft.isNotOutsideTheBoard()) {
      if (directionOfPlayIsUp) {
        possibleMoves.put(possibleMoveLeft, Direction.LEFT_UP);
      } else {
        possibleMoves.put(possibleMoveLeft, Direction.LEFT_DOWN);
      }
    }

    final PositionXY possibleMoveRight = new PositionXY(currentPositionX + 1, nextRowY);
    if (possibleMoveRight.isNotOutsideTheBoard()) {
      if (directionOfPlayIsUp) {
        possibleMoves.put(possibleMoveRight, Direction.RIGHT_UP);
      } else {
        possibleMoves.put(possibleMoveRight, Direction.RIGHT_DOWN);
      }
    }

    return possibleMoves;
  }
}
