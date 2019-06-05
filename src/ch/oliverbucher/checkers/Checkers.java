package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.controller.MainController;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.resources.Config;
import ch.oliverbucher.checkers.view.javafx.JavaFXApplication;

public class Checkers {

    public static void main(String[] args) {

        JavaFXApplication javaFXApplication = new JavaFXApplication();
        MainController controller = new MainController(javaFXApplication);

        controller.initializeGame();
        controller.startInterface();
    }

}
