package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;

import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<BoardSpace>> board;

    public Board(int width, int height) {

        board = new ArrayList<>();

        BoardColor boardColor = BoardColor.LIGHT;
        for (int i = 0; i < height; i++) {
            board.add(new ArrayList<>());
            BoardColor boardColorThisRow = boardColor;

            for (int j = 0; j < width; j++) {
                Position currentPosition = new Position(j, i);
                BoardSpace field = new BoardSpace(currentPosition, boardColorThisRow);
                board.get(i).add(field);

                boardColorThisRow = boardColorThisRow.switchColor();
            }
            boardColor = boardColor.switchColor();
        }
    }

    public ArrayList<ArrayList<BoardSpace>> getBoard() {

        return board;
    }
}
