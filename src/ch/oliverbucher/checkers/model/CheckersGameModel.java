package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.view.Message;


public class CheckersGameModel {


    private final BoardLayer boardLayer;
    private TokenLayer tokenLayer;
    private MarkLayer markLayer;

    private PlayerToken activeToken;
    private PositionXY lastClick;

    public CheckersGameModel() {

        Players.initializePlayers();
        boardLayer = new BoardLayer();
        tokenLayer = new TokenLayer(boardLayer);
        markLayer = new MarkLayer(tokenLayer);
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

        PositionXY currentClick = Positions.getPosition(x, y);
        PlayerToken currentToken = tokenLayer.get(currentClick);

        // possibilities no matter of active tokens

        // if WHITE field is being clicked
        if (!boardLayer.get(currentClick).isAllowed()) {
            Message.giveInfo("SPACE_IS_NOT_BEING_USED_FOR_THIS_GAME");

        // if field has token from opponent
        } else if (currentToken != null && currentToken.getPlayerOwner() != Players.CURRENT_PLAYER) {
            Message.giveInfo("TOKEN_BELONGS_TO_OPPONENT");

        // possibilities with no active token
        } else if (activeToken == null) {

            // if BLACK field is empty
            if (currentToken == null) {
                Message.giveInfo("SPACE_IS_EMPTY");
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();

            // if own token has no allowed moves
            } else if (!currentToken.hasAllowedMoves()) {
                Message.giveInfo("NOT_ALLOWED_TO_MOVE");
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();

            // if own token has allowed moves
            } else if (currentToken.hasAllowedMoves()) {
                Message.giveInfo("SELECTED_OWN_TOKEN");
                activeToken = currentToken;
                lastClick = currentClick;
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedMovesAndJumps(currentToken.getAllowedJumps(), currentToken.getAllowedMoves());

            }

        // if own token is active
        } else if (activeToken != null) {

            // if active token is clicked again
            if (activeToken == currentToken) {
                Message.giveInfo("TOKEN_DEACTIVATED");
                activeToken = null;
                markLayer.deactivateMarkOfCurrentClick();
                markLayer.showAllowedTokens();

            // if space is another token of current player
            } else if (tokenLayer.get(currentClick) != null && tokenLayer.get(currentClick).getPlayerOwner() == Players.CURRENT_PLAYER) {
                Message.giveInfo("ACTIVATE_NEW_TOKEN");
                activeToken = tokenLayer.get(currentClick);
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedMovesAndJumps(currentToken.getAllowedJumps(), currentToken.getAllowedMoves());

            // if move is not allowed
            } else if (!activeToken.isAllowedToMoveTo(currentClick)) {
                Message.giveInfo("MOVE_NOT_ALLOWED");

            // if move is allowed
            } else if (activeToken.isAllowedToMoveTo(currentClick)) {
                Message.giveInfo("TOKEN_MOVED");
                tokenLayer.moveToken(lastClick, currentClick);

                activeToken = null;
                Players.nextPlayer();

                tokenLayer.calculateAllPossibleMovesAndJumps();
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();
            }
        }
    }
}