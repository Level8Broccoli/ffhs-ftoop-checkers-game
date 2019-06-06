package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.BoardSpace;
import ch.oliverbucher.checkers.model.Position;
import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;

public class BoardLayer {

    private ArrayList<ArrayList<BoardSpace>> boardLayer;

    public BoardLayer() {

        boardLayer = new ArrayList<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            boardLayer.add(new ArrayList<>());

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                BoardColor currentBoardColor;
                Boolean isAllowed;
                if ((x + y) % 2 == 0) {
                    currentBoardColor = BoardColor.LIGHT;
                    isAllowed = false;
                } else {
                    currentBoardColor = BoardColor.DARK;
                    isAllowed = true;
                }

                BoardSpace field = new BoardSpace(new Position(x, y), currentBoardColor, isAllowed);
                boardLayer.get(x).add(field);
            }
        }

    }

    public ArrayList<BoardSpace> get(int x) {

        return boardLayer.get(x);
    }
}
