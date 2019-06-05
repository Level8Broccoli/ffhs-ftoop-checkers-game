package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.controller.MainController;
import ch.oliverbucher.checkers.view.javafx.JavaFXApplication;

public class Checkers {

    public static void main(String[] args) {

        JavaFXApplication javaFXApplication = new JavaFXApplication();
        MainController controller = new MainController(javaFXApplication);

        controller.initializeGame();
        controller.startInterface();
    }
}
