package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.view.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkLayer {

    private final TokenLayer tokenLayer;
    private HashMap<PositionXY, MarkType> marks;
    private PositionXY lastClicked;
    
    public MarkLayer(TokenLayer tokenLayer) {

        this.tokenLayer = tokenLayer;
        updateMarkLayer();
    }

    public void updateMarkLayer() {

        marks = new HashMap<>();

        for (PositionXY position: tokenLayer.getTokens().keySet()) {

            PlayerToken currentToken = tokenLayer.get(position);

            if (currentToken.hasAllowedMoves()) {
                marks.put(position, MarkType.POSSIBLE_MOVE);
            }
        }
    }

    public void markCurrentClick(PositionXY currentClick) {

        updateMarkLayer();
        marks.put(currentClick, MarkType.CURRENT_CLICK);
        lastClicked = currentClick;
    }

    public MarkType get(PositionXY position) {

            return marks.get(position);
    }

    public void showTokensWithAllowedMoves() {
    }

    public void showAllowedMoves(PlayerToken activeToken) {

        if (activeToken.hasAllowedMoves()) {
            ArrayList<PositionXY> allowedMoves = activeToken.getAllowedMoves();
        } else {
            Message.giveInfo("TOKEN_HAS_NO_ALLOWED_MOVES");
        }
    }
}
