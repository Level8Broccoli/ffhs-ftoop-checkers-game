package ch.oliverbucher.checkers;

import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.view.CheckersGamePresenter;

public class Checkers {

    public static void main(String[] args) {

        CheckersGameModel model = new CheckersGameModel();
        CheckersGamePresenter presenter = new CheckersGamePresenter(model);

        presenter.startApplication();
    }
}
