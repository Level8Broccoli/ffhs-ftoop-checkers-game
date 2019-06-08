package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
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
  private PositionXY activeToken;
  private boolean isSubsequentMove = false;

  public CheckersGameModel(BoardLayer boardLayer, TokenLayer tokenLayer, MarkLayer markLayer, Players players) {
    this.boardLayer = boardLayer;
    this.tokenLayer = tokenLayer;
    this.markLayer = markLayer;
    this.players = players;
  }


  public final void clickEvent(int x, int y) {

    final PositionXY currentClick = Positions.getPosition(x, y);

    // possibilities no matter what came before
    final String reason = getReasonWhyItIsNotGameChanging(currentClick);
    if (reason != null) {
      // Message.giveInfo(reason);
    } else {
      final List<AllowedMoveOrJump> allAllowedMovesAndJumps =
          tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).getMoreImportantMoves();
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
        tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).getMoreImportantMoves();
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

    if ((players.currentPlayer.isDirectionOfPlayUp() && currentMove.getEndPosition().positionY == 0)
        || (!players.currentPlayer.isDirectionOfPlayUp()
            && currentMove.getEndPosition().positionY == Config.BOARD_HEIGHT - 1)) {
      tokenLayer.upgradeTokenToKing(currentMove.getEndPosition(), players.currentPlayer);
      activeToken = null;
      players.nextPlayer();
      return;
    }

    if (currentMove.getOpponentToken() == null
        || MovesAndJumps.getEndPositionsFor(
                tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).allowedJumps, currentClick)
            .isEmpty()) {
      activeToken = null;
      players.nextPlayer();
      isSubsequentMove = false;
    } else {
      activeToken = currentClick;
      isSubsequentMove = true;
    }
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
}
