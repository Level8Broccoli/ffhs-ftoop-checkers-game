package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.Direction;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import java.util.HashMap;
import java.util.Map;

public class KingToken extends Token {

  private final TokenLayer tokens;

  public KingToken(Player playerOwner, TokenLayer tokens) {
    super(playerOwner);
    this.tokens = tokens;
  }

  @Override
  public String getName() {
    return super.getName() + "_KING";
  }

  @Override
  public Map<PositionXY, Direction> getPossibleMoves(
      PositionXY currentPosition, Positions positions) {

    Map<PositionXY, Direction> possibleMoves = new HashMap<>();

    for (Direction direction : Direction.values()) {

      PositionXY nextPosition = currentPosition;
      while (true) {
        nextPosition = getNextPositionInDirection(nextPosition, direction, positions);
        if (nextPosition == null) {
          break;
        }

        possibleMoves.put(nextPosition, direction);

        if (tokens.getTokenAt(nextPosition) != null) {
          break;
        }
      }
    }

    return possibleMoves;
  }
}
