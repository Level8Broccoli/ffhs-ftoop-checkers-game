package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.view.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkLayer {

    private final TokenLayer tokenLayer;
    private HashMap<PositionXY, MarkType> marks;

    public MarkLayer(TokenLayer tokenLayer) {

        this.tokenLayer = tokenLayer;
        marks = new HashMap<>();
        updateMarkLayer();
    }

    private void updateMarkLayer() {

        marks.clear();

        for (PositionXY position: tokenLayer.getTokens().keySet()) {

            PlayerToken currentToken = tokenLayer.get(position);

            if (currentToken.hasAllowedMoves() && currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {
                marks.put(position, MarkType.TOKEN_COULD_MOVE);
            }
        }
    }

    public void markCurrentClick(PositionXY position) {

        marks.put(position, MarkType.CURRENT_CLICK);
    }

    public void markCurrentClickAsMoved(PositionXY position) {

        marks.put(position, MarkType.TOKEN_MOVED);
    }

    public MarkType get(PositionXY position) {

            return marks.get(position);
    }

    public void showTokensWithAllowedMoves() {

        updateMarkLayer();
    }

    public void showAllowedMoves(PlayerToken activeToken) {

        marks.clear();

        if (activeToken.hasAllowedMoves()) {

            ArrayList<PositionXY> allowedMoves = activeToken.getAllowedMoves();

            allowedMoves.forEach(positionXY -> marks.put(positionXY, MarkType.POSSIBLE_MOVE));

        } else {

            Message.giveInfo("TOKEN_HAS_NO_ALLOWED_MOVES");

            updateMarkLayer();
        }
    }
}