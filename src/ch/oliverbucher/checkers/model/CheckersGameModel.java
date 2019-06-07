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


public class CheckersGameModel {


    private final BoardLayer boardLayer;
    private TokenLayer tokenLayer;
    private MarkLayer markLayer;

    private PlayerToken activeToken;

    public CheckersGameModel() {

        Players.initializePlayers();
        boardLayer = new BoardLayer();
        tokenLayer = new TokenLayer(boardLayer);
        markLayer = new MarkLayer();
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
        PlayerToken currentToken = tokenLayer.getTokenAt(currentClick);

        // possibilities no matter what came before

        // if WHITE field is being clicked
        if (!boardLayer.get(currentClick).isAllowed()) {
            Message.giveInfo("SPACE_IS_NOT_BEING_USED_FOR_THIS_GAME");

        // if field has token from opponent
        } else if (currentToken != null && currentToken.getPlayerOwner() != Players.CURRENT_PLAYER) {
            Message.giveInfo("TOKEN_BELONGS_TO_OPPONENT");

        // possibilities with no own token clicked before
        } else if (!MovesAndJumps.areEndPositionsSet()) {

            // if BLACK field is empty
            if (currentToken == null) {
                Message.giveInfo("SPACE_IS_EMPTY");
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();

            // if own token has no allowed moves/jumps
            } else if (!currentClick.hasAllowedStartMovesOrJumps()) {
                Message.giveInfo("NOT_ALLOWED_TO_MOVE_OR_JUMP");
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();

            // if own token has allowed moves/jumps
            } else if (currentClick.hasAllowedStartMovesOrJumps()) {
                Message.giveInfo("SELECTED_OWN_TOKEN");
                activeToken = currentToken;
                MovesAndJumps.setEndPositions(currentClick);

                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedEndMovesOrJumps();

            }

        // if own token has been clicked right before
        } else if (MovesAndJumps.areEndPositionsSet()) {

            // if token is clicked again
            if (activeToken == currentToken) {
                Message.giveInfo("TOKEN_DEACTIVATED");
                activeToken = null;
                MovesAndJumps.resetEndPositions();
                markLayer.deactivateMarkOfCurrentClick();
                markLayer.showAllowedTokens();

            // if space is another token of current player
            } else if (tokenLayer.getTokenAt(currentClick) != null && tokenLayer.getTokenAt(currentClick).getPlayerOwner() == Players.CURRENT_PLAYER) {
                Message.giveInfo("ACTIVATE_NEW_TOKEN");
                activeToken = tokenLayer.getTokenAt(currentClick);
                MovesAndJumps.setEndPositions(currentClick);

                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedEndMovesOrJumps();

            // if move/jump is not allowed
            } else if (!currentClick.hasAllowedEndMovesOrJumps()) {
                Message.giveInfo("MOVE_NOT_ALLOWED");

            // if move/jump is allowed
            } else if (currentClick.hasAllowedEndMovesOrJumps()) {
                Message.giveInfo("TOKEN_MOVED");
                AllowedMoveOrJump currentMove = MovesAndJumps.getMoveOrJump(currentClick);
                tokenLayer.executeMove(currentMove);

                activeToken = null;
                Players.nextPlayer();

                tokenLayer.calculateAllAllowedMovesAndJumps();
                markLayer.markCurrentClick(currentClick);
                markLayer.showAllowedTokens();
            }
        }
    }
}