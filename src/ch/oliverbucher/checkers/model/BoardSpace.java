package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace extends Space {

    private boolean isAllowed;
    private BoardColor boardColor;

    public BoardSpace(Position position, BoardColor boardColor, Boolean isAllowed) {

        super(position);
        this.boardColor = boardColor;
        this.isAllowed = isAllowed;
    }

    public BoardColor getBoardColor() {

        return boardColor;
    }

    public Boolean isAllowed() {

        return isAllowed;
    }
}
