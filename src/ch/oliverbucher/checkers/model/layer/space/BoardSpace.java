package ch.oliverbucher.checkers.model.layer.space;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.position.PositionXY;

public class BoardSpace {

    private final boolean isAllowed;
    private final BoardColor boardColor;
    private final PositionXY positionXY;

    public BoardSpace(BoardColor boardColor, Boolean isAllowed, PositionXY positionXY) {

        this.boardColor = boardColor;
        this.isAllowed = isAllowed;
        this.positionXY = positionXY;
    }

    public BoardColor getBoardColor() {

        return boardColor;
    }

    public Boolean isAllowed() {

        return isAllowed;
    }

    public PositionXY getPositionXY() {

        return positionXY;
    }
}
