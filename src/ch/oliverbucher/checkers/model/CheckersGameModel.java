package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;

public class CheckersGameModel {

    private ArrayList<ArrayList<BoardSpace>> boardLayer;
    private ArrayList<ArrayList<Token>> tokenLayer;
    private ArrayList<ArrayList<Mark>> markLayer;
    private ArrayList<ArrayList<BoardSpace>> iLayer;
    private Player[] players = new Player[2];

    public CheckersGameModel() {

        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

        generateBoardLayer(boardWidth, boardHeight);
        // generateTokenLayer(boardWidth, boardHeight);

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK);
    }

    private void generateBoardLayer(int width, int height) {

        boardLayer = new ArrayList<>();


        for (int i = 0; i < height; i++) {

            boardLayer.add(new ArrayList<>());

            for (int j = 0; j < width; j++) {

                Position currentPosition = new Position(j, i);

                BoardColor currentBoardColor;
                if ((i + j) % 2 == 0) {
                    currentBoardColor = BoardColor.LIGHT;
                } else {
                    currentBoardColor = BoardColor.DARK;
                }

                BoardSpace field = new BoardSpace(currentPosition, currentBoardColor);
                boardLayer.get(i).add(field);
            }
        }
    }

    public ArrayList<ArrayList<BoardSpace>> getBoardLayer() {

        return boardLayer;
    }

    public void setOpponent(PlayerType playerType) {

        players[1].setType(playerType);
    }
}
