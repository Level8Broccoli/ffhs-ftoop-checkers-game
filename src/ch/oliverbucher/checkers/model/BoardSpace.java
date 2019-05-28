package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumarations.BoardColor;

public class BoardSpace {
    private boolean isEmpty;
    private int coordinateX;
    private int coordinateY;
    private BoardColor boardColor;

    public BoardSpace(boolean isEmpty, int coordinateX, int coordinateY, BoardColor boardColor) {
        this.isEmpty = isEmpty;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.boardColor = boardColor;
    }
}
