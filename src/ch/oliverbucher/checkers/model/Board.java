package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class Board {

    private int sumOfSpaces;
    private BoardSpace[] boardSpaces;

    public Board(int width, int height) {

        sumOfSpaces = width * height;

        boardSpaces = new BoardSpace[sumOfSpaces];

        BoardColor boardColor = BoardColor.LIGHT;
        int counter = 0;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                Position currentPosition = new Position(j, i);
                BoardSpace field = new BoardSpace(currentPosition, boardColor);
                boardSpaces[counter] = field;

                counter++;
                boardColor = colorSwitch(boardColor);
                System.out.println(boardColor);
            }

            colorSwitch(boardColor);
        }
    }

    private BoardColor colorSwitch(BoardColor boardColor) {

        if (boardColor == BoardColor.DARK) {
            boardColor = BoardColor.LIGHT;
        } else {
            boardColor = BoardColor.DARK;
        }

        return boardColor;
    }

    public int getSumOfSpaces() {
        return sumOfSpaces;
    }

    public BoardSpace[] getBoardSpaces() {
        return boardSpaces;
    }
}
