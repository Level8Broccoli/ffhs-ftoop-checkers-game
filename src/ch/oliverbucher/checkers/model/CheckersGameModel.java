package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.resources.Config;

public class CheckersGameModel {

    private Board board;
    private Player[] players = new Player[2];

    public void initialize() {

        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

        board = new Board(boardWidth, boardHeight);

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK);
    }

    public Board getBoard() {
        return board;
    }
}
