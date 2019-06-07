package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.ArrayList;
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

        ArrayList<AllowedMoveOrJump> allowedMoveOrJumps = MovesAndJumps.getAllPossibleMovesOrJumps();

        for (AllowedMoveOrJump allowedMoveOrJump: allowedMoveOrJumps) {

            PositionXY allowedTokens = allowedMoveOrJump.getStartPosition();
            marks.put(allowedTokens, MarkType.TOKEN_COULD_MOVE_OR_JUMP);
        }

//        HashMap<PlayerToken, PlayerToken> canJump = new HashMap<>();
//        ArrayList<PlayerToken> canMove = new ArrayList<>();
//
//        for (PositionXY position: tokenLayer.getTokens().keySet()) {
//
//            PlayerToken currentToken = tokenLayer.getTokenAt(position);
//
//            // check if token is allowed to jump
//            if (currentToken.hasAllowedJumps() && currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {
//
//                // check if token could reach an opponent token
//                HashMap<PositionXY, HorizontalDirection> possibleMoves = currentToken.getPossibleMoves();
//
//                for (PositionXY moveToPosition: possibleMoves.keySet()) {
//                    PlayerToken jumpOverToken = tokenLayer.getTokenAt(moveToPosition);
//
//                    if (jumpOverToken != null && jumpOverToken.getPlayerOwner() != Players.CURRENT_PLAYER) {
//                        canJump.put(currentToken, jumpOverToken);
//                    }
//                }
//
//            // check if token can movesandjumps
//            } else if (currentToken.hasAllowedStartMovesOrJumps() && currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {
//                canMove.add(currentToken);
//            }
//        }
//
//        // if no token can jump then it is allowed to movesandjumps
//        if (canJump == null || canJump.size() == 0) {
//            canMove.forEach(playerToken ->
//                marks.put(playerToken.position, MarkType.TOKEN_COULD_MOVE_OR_JUMP);
//                    );
//        }
    }

    public void showAllowedEndMovesOrJumps() {

        HashMap<PositionXY, AllowedMoveOrJump> allowedEndMovesOrJumps = MovesAndJumps.getEndPositions();

        for (PositionXY allowedPosition: allowedEndMovesOrJumps.keySet()) {

            marks.put(allowedPosition, MarkType.POSSIBLE_MOVE);
        }

    }

    public void markCurrentClick(PositionXY currentClick) {

        marks.clear();
        marks.put(currentClick, MarkType.CURRENT_CLICK);
    }

    public MarkType get(PositionXY position) {

        return marks.get(position);
    }

    public void deactivateMarkOfCurrentClick() {

        marks.clear();
    }
}