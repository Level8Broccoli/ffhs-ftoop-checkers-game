package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.resources.Config;

public class CheckersGameModel {

    private BoardLayer boardLayer;
    private TokenLayer tokenLayer;
    private Player[] players = new Player[2];

    public CheckersGameModel() {

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE, DirectionOfPlay.UP);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK, DirectionOfPlay.DOWN);

        boardLayer = new BoardLayer();
        tokenLayer = new TokenLayer(boardLayer, players);
    }

    public void calcAllPossibleMoves() {

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {
            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
                tokenLayer.get(x).get(y).calculatePossibleMoves(tokenLayer);
            }
        }
    }

    public BoardLayer getBoardLayer() {

        return boardLayer;
    }

    public TokenLayer getTokenLayer() {

        return tokenLayer;
    }

    public void setOpponent(PlayerType playerType) {

        players[1].setPlayerType(playerType);
    }

    public void clickEvent(int x, int y) {

        System.out.println(x + " " + y);

        calcAllPossibleMoves();
    }
}