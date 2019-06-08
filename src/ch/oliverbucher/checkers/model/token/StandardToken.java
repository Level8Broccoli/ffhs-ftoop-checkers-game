package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import java.util.HashMap;
import java.util.Map;

public class StandardToken extends Token {

  public StandardToken(Player playerOwner) {
    super(playerOwner);
  }

  @Override
  public Map<PositionXY, HorizontalDirection> getPossibleMoves(PositionXY currentPosition) {

    Map<PositionXY, HorizontalDirection> possibleMoves = new HashMap<>();

    final int currentPositionX = currentPosition.positionX;
    final int currentPositionY = currentPosition.positionY;
    final int nextRowY =
        playerOwner.getDirectionOfPlay().isUp() ? currentPositionY - 1 : currentPositionY + 1;

    final PositionXY possibleMoveLeft = Positions.getPosition(currentPositionX - 1, nextRowY);
    if (possibleMoveLeft != null) {
      possibleMoves.put(possibleMoveLeft, HorizontalDirection.LEFT);
    }

    final PositionXY possibleMoveRight = Positions.getPosition(currentPositionX + 1, nextRowY);
    if (possibleMoveRight != null) {
      possibleMoves.put(possibleMoveRight, HorizontalDirection.RIGHT);
    }

    return possibleMoves;
  }
}
