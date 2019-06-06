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

        for (int x = 0; x < boardWidth; x++) {

            boardLayer.add(new ArrayList<>());

            for (int y = 0; y < boardHeight; y++) {

                BoardColor currentBoardColor;
                Boolean isAllowed;
                if ((x + y) % 2 == 0) {
                    currentBoardColor = BoardColor.LIGHT;
                    isAllowed = false;
                } else {
                    currentBoardColor = BoardColor.DARK;
                    isAllowed = true;
                }

                BoardSpace field = new BoardSpace(new Position(y, x), currentBoardColor, isAllowed);
                boardLayer.get(x).add(field);
            }
        }
    }

    private void generateTokenLayer(int boardWidth, int boardHeight) {

        tokenLayer = new ArrayList<>();
        int startRows = Integer.parseInt(Config.getValue("START_ROWS"));

        for (int x = 0; x < boardWidth; x++) {

            tokenLayer.add(new ArrayList<>());

            if (x < startRows ) {

                for (int y = 0; y < boardHeight; y++) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new Token(new Position(y, x), players[1]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(y, x), true));
                    }
                }
            } else if (x >= startRows && x < boardHeight - startRows) {

                for (int y = 0; y < boardHeight; y++) {

                    tokenLayer.get(x).add(new Token(new Position(y, x), true));
                }
            } else if (x >= boardHeight - startRows) {

                for (int y = 0; y < boardHeight; y++) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new Token(new Position(y, x), players[0]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(y, x), true));
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
