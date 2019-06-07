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
        final Map<PositionXY, AllowedMoveOrJump> endPositionsStartingFromActiveToken =
                MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps,
                        activeToken);

        // if token is clicked again
        if (activeToken == currentClick) {
            Message.giveInfo("TOKEN_DEACTIVATED");
            activeToken = null;
            markLayer.deactivateMarkOfCurrentClick();
            markLayer.showAllowedTokens(allAllowedMovesAndJumps);
        // if space is another token of current player
        } else if (tokenLayer.getTokenAt(currentClick) != null && tokenLayer.getTokenAt(currentClick).getPlayerOwner() == Players.CURRENT_PLAYER) {
            Message.giveInfo("ACTIVATE_NEW_TOKEN");
            activeToken = currentClick;

            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedEndMovesOrJumps(endPositionsStartingFromActiveToken);
        // if move/jump is not allowed
        } else if (!endPositionsStartingFromActiveToken.containsKey(currentClick)) {
            Message.giveInfo("MOVE_NOT_ALLOWED");
        // if move/jump is allowed
        } else if (endPositionsStartingFromActiveToken.containsKey(currentClick)) {
            Message.giveInfo("TOKEN_MOVED");
            AllowedMoveOrJump currentMove =
                    endPositionsStartingFromActiveToken.get(currentClick);

            tokenLayer.executeMove(currentMove);

            activeToken = null;
            Players.nextPlayer();

            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedTokens(tokenLayer.getAllAllowedMovesAndJumps());
        }
    }

    private void tryClickToActivateToken(PositionXY currentClick, List<AllowedMoveOrJump> allAllowedMovesAndJumps) {
        final Map<PositionXY, AllowedMoveOrJump> endPositionsStartingFromCurrentClick =
                MovesAndJumps.getEndPositionsFor(allAllowedMovesAndJumps,
                        currentClick);

        // if BLACK field is empty
        if (tokenLayer.getTokenAt(currentClick) == null) {
            Message.giveInfo("SPACE_IS_EMPTY");
            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedTokens(allAllowedMovesAndJumps);
        // if own token has no allowed moves/jumps
        } else if (endPositionsStartingFromCurrentClick.isEmpty()) {
            Message.giveInfo("NOT_ALLOWED_TO_MOVE_OR_JUMP");
            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedTokens(allAllowedMovesAndJumps);
        // if own token has allowed moves/jumps
        } else if (!endPositionsStartingFromCurrentClick.isEmpty()) {
            Message.giveInfo("SELECTED_OWN_TOKEN");
            activeToken = currentClick;

            markLayer.markCurrentClick(currentClick);
            markLayer.showAllowedEndMovesOrJumps(endPositionsStartingFromCurrentClick);
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