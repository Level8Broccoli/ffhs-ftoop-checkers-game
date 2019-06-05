package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.controller.CheckersGameController;
import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.view.CheckersGameView;

public class Checkers {

    public static void main(String[] args) {

        CheckersGameModel model = new CheckersGameModel();
        CheckersGameView view = new CheckersGameView(model);
        CheckersGameController controller = new CheckersGameController(model, view);

        controller.startApplication();
    }
}
