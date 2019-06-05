package ch.oliverbucher.checkers.controller;

import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.ApplicationInterface;

public class MainController {

    private Board board;
    private ApplicationInterface view;
    private Player[] players;

    public MainController(ApplicationInterface view) {

        this.view = view;
    }

    public void initializeGame() {

        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

        board = new Board(boardWidth,boardHeight);

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK);
    }

    public void startInterface() {

        view.startGame();
    }
}
