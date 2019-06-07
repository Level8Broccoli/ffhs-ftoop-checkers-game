package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.JumpDirections;
import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.PlayerToken;

import java.util.HashMap;

public class MarkLayer {

    private final TokenLayer tokenLayer;
    private HashMap<PositionXY, MarkType> marks;

    public MarkLayer(TokenLayer tokenLayer) {

        this.tokenLayer = tokenLayer;
        marks = new HashMap<>();
        showAllowedTokens();
    }

    public void showAllowedTokens() {

        for (PositionXY position: tokenLayer.getTokens().keySet()) {

            PlayerToken currentToken = tokenLayer.get(position);

            if (currentToken.hasAllowedMoves() && currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {
                marks.put(position, MarkType.TOKEN_COULD_MOVE);
            }
        }
    }

    public void markCurrentClick(PositionXY currentClick) {

        marks.clear();
        marks.put(currentClick, MarkType.CURRENT_CLICK);
    }

    public MarkType get(PositionXY position) {

        return marks.get(position);
    }

    public void showAllowedMovesAndJumps(HashMap<PositionXY, JumpDirections> allowedJumps, HashMap<PositionXY, JumpDirections> allowedMoves) {

        allowedMoves.forEach((positionXY, directions) -> marks.put(positionXY, MarkType.POSSIBLE_MOVE));
    }

    public void deactivateMarkOfCurrentClick() {

        marks.clear();
    }
}