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

    private static int BOARD_WIDTH = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
    private static int BOARD_HEIGHT = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

    public CheckersGameModel() {

        generateBoardLayer(BOARD_WIDTH, BOARD_HEIGHT);
        generateTokenLayer(BOARD_WIDTH, BOARD_HEIGHT);

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK);
    }

    private void generateBoardLayer(int boardWidth, int boardHeight) {

        boardLayer = new ArrayList<>();

        for (int i = 0; i < boardWidth; i++) {

            boardLayer.add(new ArrayList<>());

            for (int j = 0; j < boardHeight; j++) {

                BoardColor currentBoardColor;
                Boolean isAllowed;
                if ((i + j) % 2 == 0) {
                    currentBoardColor = BoardColor.LIGHT;
                    isAllowed = false;
                } else {
                    currentBoardColor = BoardColor.DARK;
                    isAllowed = true;
                }

                BoardSpace field = new BoardSpace(new Position(j, i), currentBoardColor, isAllowed);
                boardLayer.get(i).add(field);
            }
        }
    }

    private void generateTokenLayer(int boardWidth, int boardHeight) {

        tokenLayer = new ArrayList<>();
        int startRows = Integer.parseInt(Config.getValue("START_ROWS"));

        for (int i = 0; i < boardWidth; i++) {

            tokenLayer.add(new ArrayList<>());

            if (i < startRows ) {

                for (int j = 0; j < boardHeight; j++) {

                    if (boardLayer.get(i).get(j).isAllowed()) {
                        tokenLayer.get(i).add(new Token(new Position(j, i), players[1]));
                    } else {
                        tokenLayer.get(i).add(new Token(new Position(j, i), true));
                    }
                }
            } else if (i >= startRows && i < boardHeight - startRows) {

                for (int j = 0; j < boardHeight; j++) {

                    tokenLayer.get(i).add(new Token(new Position(j, i), true));
                }
            } else if (i >= boardHeight - startRows) {

                for (int j = 0; j < boardHeight; j++) {

                    if (boardLayer.get(i).get(j).isAllowed()) {
                        tokenLayer.get(i).add(new Token(new Position(j, i), players[0]));
                    } else {
                        tokenLayer.get(i).add(new Token(new Position(j, i), true));
                    }
                }
            }
        }
    }

    public ArrayList<ArrayList<BoardSpace>> getBoardLayer() {

        return boardLayer;
    }

    public ArrayList<ArrayList<Token>> getTokenLayer() {

        return tokenLayer;
    }

    public void setOpponent(PlayerType playerType) {

        players[1].setType(playerType);
    }

}
