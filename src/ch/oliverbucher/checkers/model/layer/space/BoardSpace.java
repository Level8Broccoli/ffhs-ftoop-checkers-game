package ch.oliverbucher.checkers.model.layer.space;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.Position;

public class BoardSpace {

    private final boolean isAllowed;
    private final BoardColor boardColor;
    private final Position position;

    public BoardSpace(BoardColor boardColor, Boolean isAllowed, Position position) {

        this.boardColor = boardColor;
        this.isAllowed = isAllowed;
        this.position = position;
    }

    public BoardColor getBoardColor() {

        return boardColor;
    }

    public Boolean isAllowed() {

        return isAllowed;
    }

    public Position getPosition() {

        return position;
    }
}
