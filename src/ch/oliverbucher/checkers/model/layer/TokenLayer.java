package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMovesAndJumps;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.KingToken;
import ch.oliverbucher.checkers.model.token.StandardToken;
import ch.oliverbucher.checkers.model.token.Token;
import ch.oliverbucher.checkers.resources.Config;
import java.util.HashMap;
import java.util.Map;

public class TokenLayer {

  private Map<PositionXY, Token> tokens;

  public final void generateTokenLayer(
      BoardLayer boardLayer, Players players, Positions positions) {

    tokens = new HashMap<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {

      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        PositionXY currentPosition = positions.getPosition(x, y);

        if (y < Config.START_ROWS) {

          if (boardLayer.get(currentPosition).isAllowed()) {

            tokens.put(currentPosition, new StandardToken(players.players[1]));
          }

        } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

          if (boardLayer.get(currentPosition).isAllowed()) {

            tokens.put(currentPosition, new StandardToken(players.players[0]));
          }
        }
      }
    }
  }

  public final AllowedMovesAndJumps getAllAllowedMovesAndJumps(
      Player currentPlayer, Positions positions) {
    return MovesAndJumps.getAllAllowedMovesAndJumps(tokens, currentPlayer, positions);
  }

  public final Token getTokenAt(PositionXY positionXY) {
    return tokens.get(positionXY);
  }

  public final void executeMove(AllowedMoveOrJump currentMove) {
    PositionXY startPosition = currentMove.getStartPosition();
    PositionXY endPosition = currentMove.getEndPosition();
    PositionXY opponentToken = currentMove.getOpponentToken();

    if (opponentToken != null) {
      tokens.remove(opponentToken);
    }

    Token currentToken = tokens.get(startPosition);
    tokens.remove(startPosition);
    tokens.put(endPosition, currentToken);
  }

  public void upgradeTokenToKing(PositionXY position, Player currentPlayer) {
    tokens.remove(position);
    tokens.put(position, new KingToken(currentPlayer, this));
  }
}
