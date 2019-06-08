package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMovesAndJumps;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.resources.Config;
import java.util.HashMap;
import java.util.Map;

public class TokenLayer {

  private Map<PositionXY, PlayerToken> tokens;

  public final void generateTokenLayer(BoardLayer boardLayer, Player[] player) {

    tokens = new HashMap<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {

      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        PositionXY currentPosition = Positions.getPosition(x, y);

        if (y < Config.START_ROWS) {

          if (boardLayer.get(currentPosition).isAllowed()) {

            tokens.put(currentPosition, new PlayerToken(player[1]));
          }

        } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

          if (boardLayer.get(currentPosition).isAllowed()) {

            tokens.put(currentPosition, new PlayerToken(player[0]));
          }
        }
      }
    }
  }

  public final AllowedMovesAndJumps getAllAllowedMovesAndJumps(Player currentPlayer) {

    return MovesAndJumps.getAllAllowedMovesAndJumps(tokens, currentPlayer);
  }

  public final PlayerToken getTokenAt(PositionXY positionXY) {

    return tokens.get(positionXY);
  }

  public final void executeMove(AllowedMoveOrJump currentMove) {

    PositionXY startPosition = currentMove.getStartPosition();
    PositionXY endPosition = currentMove.getEndPosition();
    PositionXY opponentToken = currentMove.getOpponentToken();

    if (opponentToken != null) {
      tokens.remove(opponentToken);
    }

    PlayerToken currentToken = tokens.get(startPosition);
    tokens.remove(startPosition);
    tokens.put(endPosition, currentToken);
  }
}
