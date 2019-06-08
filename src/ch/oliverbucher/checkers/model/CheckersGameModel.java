package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.Token;
import ch.oliverbucher.checkers.resources.Config;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CheckersGameModel {

  private final BoardLayer boardLayer;
  private final TokenLayer tokenLayer;
  private final MarkLayer markLayer;
  private final Players players;
  private PositionXY activeToken;
  private PositionXY currentClick;
  private boolean isSubsequentMove = false;
  private Player loser = null;

  public CheckersGameModel(
      BoardLayer boardLayer, TokenLayer tokenLayer, MarkLayer markLayer, Players players) {
    this.boardLayer = boardLayer;
    this.tokenLayer = tokenLayer;
    this.markLayer = markLayer;
    this.players = players;
  }

  public final void clickEvent(int x, int y) {

    currentClick = new PositionXY(x, y);

    final String reason = getReasonWhyItIsNotGameChanging();
    if (reason == null) {
      final List<AllowedMoveOrJump> allAllowedMovesAndJumps =
          tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).getMoreImportantMoves();
      if (activeToken == null) {
        tryClickToActivateToken(currentClick, allAllowedMovesAndJumps);
      } else {
        tryClickToMove(currentClick, allAllowedMovesAndJumps);
      }
    }

    updateUi();
  }

  private void updateUi() {
    final List<AllowedMoveOrJump> allAllowedMovesAndJumps =
        tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).getMoreImportantMoves();

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
    if (activeToken.equals(currentClick) && !isSubsequentMove) {
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
                tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).allowedJumps,
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
    isSubsequentMove = false;

    players.nextPlayer();

    if (players.currentPlayer.isComputerPlayer()) {
      updateUi();
      computerMakeRandomMove();
    }
  }

  private void computerMakeRandomMove() {
    List<AllowedMoveOrJump> allowedMovesOrJumps =
        tokenLayer.getAllAllowedMovesAndJumps(players.currentPlayer).getMoreImportantMoves();

    if (allowedMovesOrJumps.size() == 0) {
      return;
    }

    final Random randomGenerator = new Random();
    final int randomMoveNumber = randomGenerator.nextInt(allowedMovesOrJumps.size());
    final AllowedMoveOrJump randomSelectedMove = allowedMovesOrJumps.get(randomMoveNumber);

    final PositionXY startPosition = randomSelectedMove.getStartPosition();
    final PositionXY endPosition = randomSelectedMove.getEndPosition();

    clickEvent(startPosition.positionX, startPosition.positionY);
    clickEvent(endPosition.positionX, endPosition.positionY);

    if (players.currentPlayer.isComputerPlayer()) {
      updateUi();
      computerMakeRandomMove();
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

  private String getReasonWhyItIsNotGameChanging() {
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
