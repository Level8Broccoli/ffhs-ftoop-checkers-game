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
    private Player[] players = new Player[2];

    public CheckersGameModel() {

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK);

        generateBoardLayer();
        generateTokenLayer();
//        generateMarkLayer();
    }

    private void generateBoardLayer() {

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

                BoardSpace field = new BoardSpace(new Position(y, x), currentBoardColor, isAllowed);
                boardLayer.get(x).add(field);
            }
        }
    }

    private void generateTokenLayer() {

        tokenLayer = new ArrayList<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            tokenLayer.add(new ArrayList<>());

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
                if (y < Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new Token(new Position(y, x), players[1]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(y, x)));
                    }

                } else if (y >= Config.START_ROWS && y < Config.BOARD_HEIGHT - Config.START_ROWS) {

                    tokenLayer.get(x).add(new Token(new Position(y, x)));

                } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new Token(new Position(y, x), players[0]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(y, x)));
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

        players[1].setPlayerType(playerType);
    }
}