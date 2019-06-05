package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.controller.MainController;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.javafx.JavaFXApplication;

public class Checkers {

    private static Board board;
    private static Player[] players;

    public static void main(String[] args) {

        initializeGame();

        JavaFXApplication javaFXApplication = new JavaFXApplication();
        MainController controller = new MainController(javaFXApplication, board, players);

        controller.startInterface();
    }

    private static void initializeGame() {

        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

        board = new Board(boardWidth,boardHeight);

        Player playerOne = new Player(PlayerType.HUMAN, PlayerColor.WHITE);
        Player playerTwo = new Player(PlayerType.HUMAN, PlayerColor.BLACK);

        players[0] = playerOne;
        players[1] = playerTwo;
    }
}
