package ch.oliverbucher.checkers.controller;

import ch.oliverbucher.checkers.model.CheckersGameModel;
import ch.oliverbucher.checkers.view.CheckersGameView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersGameController {

    private final CheckersGameModel model;
    private CheckersGameView view;

    private Scene launchScene;
    private Scene gameScene;
    private Stage stage;

    public CheckersGameController(CheckersGameModel model, CheckersGameView view) {

        this.model = model;
        this.view = view;
    }

    public void startApplication() {

        model.initialize();
        view.startGame();
        initializeListener();
    }

    private void initializeListener() {
        gameScene = view.getGameScene();
        launchScene = view.getLaunchScene();
        stage = view.getStage();

        if (view.getBtnNewGame() != null) {
            view.getBtnNewGame().setOnAction(this::onClickNewGame);
        }

        if (view.getBtnStartGame() != null) {
            view.getBtnStartGame().setOnAction(this::onClickStartGame);
        }
    }

    private void onClickNewGame(ActionEvent event) {

        System.out.println("New Game");
        stage.setScene(launchScene);
    }

    private void onClickStartGame(ActionEvent event) {

        System.out.println("Start Game");
        stage.setScene(gameScene);
    }
}