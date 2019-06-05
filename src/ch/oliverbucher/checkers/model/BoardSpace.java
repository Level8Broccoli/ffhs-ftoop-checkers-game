package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace {
    private boolean isEmpty;
    private Position position;
    private BoardColor boardColor;

    public BoardSpace(Position position, BoardColor boardColor) {
        this.position = position;
        this.boardColor = boardColor;
    }
}
