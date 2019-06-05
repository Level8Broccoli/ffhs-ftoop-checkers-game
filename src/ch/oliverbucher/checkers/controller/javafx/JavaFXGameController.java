package ch.oliverbucher.checkers.controller.javafx;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.Board;
import ch.oliverbucher.checkers.model.BoardSpace;
import ch.oliverbucher.checkers.resources.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXGameController implements Initializable {

    @FXML
    private BorderPane container;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane boardContainer;

    private Scene launchScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnNewGame.setOnAction(this::onClick);

        drawBoard();
    }

    private void drawBoard() {

        int boardWidth = Integer.parseInt(Config.getValue("BOARD_WIDTH"));
        int boardHeight = Integer.parseInt(Config.getValue("BOARD_HEIGHT"));

        Board board = new Board(boardWidth, boardHeight);

        for (int i = 0; i < board.getSumOfSpaces(); i++) {
            BoardSpace currentSpace = board.getBoardSpaces()[i];

            int currentPositionX = currentSpace.getPosition().getPositionX();
            int currentPositionY = currentSpace.getPosition().getPositionY();
            BoardColor currentBoardColor = currentSpace.getBoardColor();

            Label label = new Label("Text");
            label.setId(String.valueOf(currentBoardColor));

            boardContainer.add(label, currentPositionX, currentPositionY);
        }
    }

    private void onClick(ActionEvent event) {

        newGame();
    }

    private void newGame() {

        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(launchScene);
    }

    public void setLaunchScene(Scene launchScene) {
        this.launchScene = launchScene;
    }
}
