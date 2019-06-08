package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.Token;
import ch.oliverbucher.checkers.resources.Config;
import java.util.List;
import java.util.Map;

public class CheckersGameModel {

  private final BoardLayer boardLayer;
  private final TokenLayer tokenLayer;
  private final MarkLayer markLayer;
  private final Players players;
  private final Positions positions;
  private PositionXY activeToken;
  private boolean isSubsequentMove = false;
  private Player loser = null;

  public CheckersGameModel(
      BoardLayer boardLayer,
      TokenLayer tokenLayer,
      MarkLayer markLayer,
      Players players,
      Positions positions) {
    this.boardLayer = boardLayer;
    this.tokenLayer = tokenLayer;
    this.markLayer = markLayer;
    this.players = players;
    this.positions = positions;
  }

  public final void clickEvent(int x, int y) {

    final PositionXY currentClick = positions.getPosition(x, y);

    // possibilities no matter what came before
    final String reason = getReasonWhyItIsNotGameChanging(currentClick);
    if (reason != null) {
      // Message.giveInfo(reason);
    } else {
      final List<AllowedMoveOrJump> allAllowedMovesAndJumps =
          tokenLayer
              .getAllAllowedMovesAndJumps(players.currentPlayer, positions)
              .getMoreImportantMoves();
      if (activeToken == null) {
        tryClickToActivateToken(currentClick, allAllowedMovesAndJumps);
      } else {
        tryClickToMove(currentClick, allAllowedMovesAndJumps);
      }
    }

    updateUi(currentClick);
  }

  private void updateUi(PositionXY currentClick) {
    final List<AllowedMoveOrJump> allAllowedMovesAndJumps =
        tokenLayer
            .getAllAllowedMovesAndJumps(players.currentPlayer, positions)
            .getMoreImportantMoves();

    if (allAllowedMovesAndJumps.isEmpty()) {
      loser = players.currentPlayer;
    }

    if (activeToken == null) {
      Token tokenAtClick = tokenLayer.getTokenAt(currentClick);
      if (tokenAtClick == null) {
        markLayer.showAllowedTokens(currentClick, allAllowedMovesAndJumps);
      } else {
        markLayer.showAllowedTokens(null, allAllowedMovesAndJumps);
      }
    } else {
      markLayer.showAllowedEndMovesOrJumps(
          activeToken, MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps, activeToken));
    }
  }

  private void tryClickToMove(
      PositionXY currentClick, List<AllowedMoveOrJump> allAllowedMovesAndJumps) {
    final Map<PositionXY, AllowedMoveOrJump> possibleMovesEndingAt =
        MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps, activeToken);
    if (activeToken == currentClick && !isSubsequentMove) {
      activeToken = null;
      return;
    }

    if (tokenLayer.getTokenAt(currentClick) != null
        && tokenLayer.getTokenAt(currentClick).getPlayerOwner() == players.currentPlayer) {
      activeToken = currentClick;
      return;
    }

    if (!possibleMovesEndingAt.containsKey(currentClick)) {
      return;
    }

    clickToMove(currentClick, possibleMovesEndingAt);
  }

  private void clickToMove(
      PositionXY currentClick, Map<PositionXY, AllowedMoveOrJump> possibleMovesEndingAt) {
    AllowedMoveOrJump currentMove = possibleMovesEndingAt.get(currentClick);

    tokenLayer.executeMove(currentMove);

    final int endRowOfCurrentPlayer =
        players.currentPlayer.isDirectionOfPlayUp() ? 0 : Config.BOARD_HEIGHT - 1;

    if (currentMove.getEndPosition().positionY == endRowOfCurrentPlayer) {
      tokenLayer.upgradeTokenToKing(currentMove.getEndPosition(), players.currentPlayer);
      endOfTurn();
      return;
    }

    if (currentMove.getOpponentToken() == null
        || MovesAndJumps.getEndPositionsFor(
                tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer, positions)
                    .allowedJumps,
                currentClick)
            .isEmpty()) {
      endOfTurn();
    } else {
      activeToken = currentClick;
      isSubsequentMove = true;
    }
  }

  private void endOfTurn() {
    activeToken = null;
    players.nextPlayer();
    isSubsequentMove = false;
  }

  private void tryClickToActivateToken(
      PositionXY currentClick, List<AllowedMoveOrJump> allAllowedMovesAndJumps) {
    final Map<PositionXY, AllowedMoveOrJump> possibleMoves =
        MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps, currentClick);

    if (!possibleMoves.isEmpty() && !isSubsequentMove) {
      activeToken = currentClick;
    }
  }

  private String getReasonWhyItIsNotGameChanging(PositionXY currentClick) {
    Token currentToken = tokenLayer.getTokenAt(currentClick);
    if (!boardLayer.get(currentClick).isAllowed()) {
      return "SPACE_IS_NOT_BEING_USED_FOR_THIS_GAME";
    } else if (currentToken != null && currentToken.getPlayerOwner() != players.currentPlayer) {
      return "TOKEN_BELONGS_TO_OPPONENT";
    }
    return null;
  }

  public Player whoIsTheWinner() {
    return loser;
  }
}
