package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace {
    private boolean isEmpty;
    private Position position;
    private BoardColor boardColor;

    public BoardSpace(boolean isEmpty, Position position, BoardColor boardColor) {
        this.isEmpty = isEmpty;
        this.position = position;
        this.boardColor = boardColor;
    }
}
