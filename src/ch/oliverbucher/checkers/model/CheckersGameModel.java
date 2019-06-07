package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.view.Message;

import java.util.List;
import java.util.Map;


public class CheckersGameModel {

    private final BoardLayer boardLayer;
    private TokenLayer tokenLayer;
    private MarkLayer markLayer;

    private PositionXY activeToken;

    public CheckersGameModel() {

        Players.initializePlayers();
        boardLayer = new BoardLayer();
        tokenLayer = new TokenLayer(boardLayer);
        markLayer = new MarkLayer();
        markLayer.showAllowedTokens(tokenLayer.getAllAllowedMovesAndJumps());
    }

    public BoardLayer getBoardLayer() {

        return boardLayer;
    }

    public TokenLayer getTokenLayer() {

        return tokenLayer;
    }

    public MarkLayer getMarkLayer() {

        return markLayer;
    }

    public void setOpponent(PlayerType playerType) {

        Players.getPlayer(1).setPlayerType(playerType);
    }

    public void clickEvent(int x, int y) {

        final PositionXY currentClick = Positions.getPosition(x, y);

        // possibilities no matter what came before
        final String reason = getReasonWhyItIsNotGameChanging(currentClick);
        if (reason != null) {
            Message.giveInfo(reason);
            return;
        }
        final List<AllowedMoveOrJump> allAllowedMovesAndJumps = tokenLayer.getAllAllowedMovesAndJumps();

        if (activeToken == null) {
            tryClickToActivateToken(currentClick, allAllowedMovesAndJumps);
        } else {
            tryClickToMove(currentClick, allAllowedMovesAndJumps);
        }
    }

    private void tryClickToMove(PositionXY currentClick, List<AllowedMoveOrJump> allAllowedMovesAndJumps) {
        final Map<PositionXY, AllowedMoveOrJump> possibleMovesEndingAt =
                MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps,
                        activeToken);
        if (activeToken == currentClick) {
            activeToken = null;
            markLayer.deactivateMarkOfCurrentClick();
            markLayer.showAllowedTokens(allAllowedMovesAndJumps);
            return;
        }

        // if token is clicked again
        if (tokenLayer.getTokenAt(currentClick) != null && tokenLayer.getTokenAt(currentClick).getPlayerOwner() == Players.CURRENT_PLAYER) {
            activeToken = currentClick;
            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedEndMovesOrJumps(possibleMovesEndingAt);
            return;
        }

        if (!possibleMovesEndingAt.containsKey(currentClick)) {
            return;
        }

        clickToMove(currentClick, possibleMovesEndingAt);
    }

    private void clickToMove(PositionXY currentClick, Map<PositionXY, AllowedMoveOrJump> possibleMovesEndingAt) {
        AllowedMoveOrJump currentMove = possibleMovesEndingAt.get(currentClick);

        tokenLayer.executeMove(currentMove);

        activeToken = null;
        Players.nextPlayer();

        markLayer.markCurrentClick(currentClick);
        markLayer.showAllowedTokens(tokenLayer.getAllAllowedMovesAndJumps());
    }

    private void tryClickToActivateToken(PositionXY currentClick, List<AllowedMoveOrJump> allAllowedMovesAndJumps) {
        final Map<PositionXY, AllowedMoveOrJump> possibleMoves =
                MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps,
                        currentClick);

        markLayer.markCurrentClick(currentClick);

        if (possibleMoves.isEmpty()) {
            markLayer.showAllowedTokens(allAllowedMovesAndJumps);
        } else {
            activeToken = currentClick;
            markLayer.showAllowedEndMovesOrJumps(possibleMoves);
        }
    }

    private String getReasonWhyItIsNotGameChanging(PositionXY currentClick) {
        PlayerToken currentToken = tokenLayer.getTokenAt(currentClick);
        if (!boardLayer.get(currentClick).isAllowed()) {
            return "SPACE_IS_NOT_BEING_USED_FOR_THIS_GAME";
        } else if (currentToken != null && currentToken.getPlayerOwner() != Players.CURRENT_PLAYER) {
            return "TOKEN_BELONGS_TO_OPPONENT";
        }
        return null;
    }
}