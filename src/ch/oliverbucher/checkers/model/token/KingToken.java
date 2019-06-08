package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.Direction;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import java.util.HashMap;
import java.util.Map;

public class KingToken extends Token {

  public KingToken(Player playerOwner) {
    super(playerOwner);
  }

  @Override
  public Map<PositionXY, Direction> getPossibleMoves(PositionXY currentPosition) {

    Map<PositionXY, Direction> possibleMoves = new HashMap<>();

    for (Direction direction : Direction.values()) {
      while (true) {
        final PositionXY nextPosition = getNextPositionInDirection(currentPosition, direction);
        if (nextPosition == null) {
          break;
        }
        possibleMoves.put(nextPosition, direction);
      }
    }

    return possibleMoves;
  }
}
