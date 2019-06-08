package ch.oliverbucher.checkers.model.movesandjumps;

import ch.oliverbucher.checkers.enumaration.Direction;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovesAndJumps {

  public static AllowedMovesAndJumps getAllAllowedMovesAndJumps(
      Map<PositionXY, Token> tokens, Player currentPlayer) {
    AllowedMovesAndJumps allowedMovesAndJumps = new AllowedMovesAndJumps();

    for (PositionXY currentPosition : tokens.keySet()) {

      Token currentToken = tokens.get(currentPosition);

      if (currentToken.getPlayerOwner() == currentPlayer) {

        Map<PositionXY, Direction> possibleMoves =
            currentToken.getPossibleMoves(currentPosition);

        for (PositionXY possibleMovePosition : possibleMoves.keySet()) {

          if (tokens.get(possibleMovePosition) == null) {

            AllowedMoveOrJump possibleMove =
                new AllowedMoveOrJump(currentPosition, possibleMovePosition);
            allowedMovesAndJumps.allowedMoves.add(possibleMove);

          } else if (tokens.get(possibleMovePosition).getPlayerOwner() != currentPlayer) {

            Direction direction = possibleMoves.get(possibleMovePosition);

            PositionXY positionBehindOpponent =
                currentToken.getNextPositionInDirection(possibleMovePosition, direction);

            if (positionBehindOpponent != null && tokens.get(positionBehindOpponent) == null) {
              AllowedMoveOrJump possibleJump =
                  new AllowedMoveOrJump(
                      currentPosition, positionBehindOpponent, possibleMovePosition);
              allowedMovesAndJumps.allowedJumps.add(possibleJump);
            }
          }
        }
      }
    }
    return allowedMovesAndJumps;
  }

  public static Map<PositionXY, AllowedMoveOrJump> getEndPositionsFor(
      List<AllowedMoveOrJump> allAllowedMovesOrJumps, PositionXY currentClick) {

    Map<PositionXY, AllowedMoveOrJump> result = new HashMap<>();

    for (AllowedMoveOrJump allowedMoveOrJump : allAllowedMovesOrJumps) {
      if (allowedMoveOrJump.getStartPosition() == currentClick) {
        result.put(allowedMoveOrJump.getEndPosition(), allowedMoveOrJump);
      }
    }

    return result;
  }
}
