package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace {

    private boolean isAllowed;
    private Position position;
    private BoardColor boardColor;

    public BoardSpace(Position position, BoardColor boardColor, Boolean isAllowed) {

        this.position = position;
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
