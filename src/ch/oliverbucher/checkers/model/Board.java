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
            BoardColor boardColorThisRow = boardColor;

            for (int j = 1; j <= width; j++) {
                Position currentPosition = new Position(j, i);
                BoardSpace field = new BoardSpace(currentPosition, boardColorThisRow);
                boardSpaces[counter] = field;

                counter++;
                boardColorThisRow = colorSwitch(boardColorThisRow);
            }
            boardColor = colorSwitch(boardColor);
        }
    }

    private BoardColor colorSwitch(BoardColor boardColor) {

        if (boardColor == BoardColor.DARK) {
            return BoardColor.LIGHT;
        } else {
            return BoardColor.DARK;
        }
    }

    public int getSumOfSpaces() {
        return sumOfSpaces;
    }

    public BoardSpace[] getBoardSpaces() {
        return boardSpaces;
    }
}
