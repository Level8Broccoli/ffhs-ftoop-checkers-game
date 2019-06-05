package ch.oliverbucher.checkers.controller;

import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.view.ApplicationInterface;

public class MainController {

    private ApplicationInterface view;
    private Board boardModel;
    private Player[] players;

    public MainController(ApplicationInterface view, Board boardModel, Player[] players) {

        this.view = view;
        this.boardModel = boardModel;
        this.players = players;
    }

    public void startInterface() {

        view.startGame();
    }
}
