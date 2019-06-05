package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class Board {
    private int width;
    private int height;


    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        int sumOfSpaces = width * height;

        BoardSpace[] boardSpaces = new BoardSpace[sumOfSpaces];
        int counter = 0;

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                Position currentPosition = new Position(j, i);
                BoardSpace field = new BoardSpace(currentPosition, BoardColor.DARK);
                boardSpaces[counter++] = field;
            }
        }
    }
}
