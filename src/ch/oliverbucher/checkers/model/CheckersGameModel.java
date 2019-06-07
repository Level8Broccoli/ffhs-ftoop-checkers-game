package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.position.Positions;

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

        System.out.println(tokenLayer.get(Positions.getPosition(x, y)));
        System.out.println(x + " " + y);
    }
}