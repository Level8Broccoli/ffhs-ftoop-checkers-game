package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkLayer {

    private HashMap<PositionXY, MarkType> marks;

    public MarkLayer() {

        marks = new HashMap<>();
        showAllowedTokens();
    }

    public void showAllowedTokens() {

        List<AllowedMoveOrJump> allowedMoveOrJumps = MovesAndJumps.getAllAllowedMovesOrJumps();

        for (AllowedMoveOrJump allowedMoveOrJump: allowedMoveOrJumps) {

            PositionXY allowedTokens = allowedMoveOrJump.getStartPosition();
            marks.put(allowedTokens, MarkType.TOKEN_COULD_MOVE_OR_JUMP);
        }
    }

    public void showAllowedEndMovesOrJumps() {

        Map<PositionXY, AllowedMoveOrJump> allowedEndMovesOrJumps = MovesAndJumps.endPositions;

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