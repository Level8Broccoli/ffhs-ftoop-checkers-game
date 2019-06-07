package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

public class CheckersGameModel {

    private final Player[] players = new Player[2];

    private final BoardLayer boardLayer;
    private TokenLayer tokenLayer;

    private PositionXY currentClick;
    private PositionXY lastClick;

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

        currentClick = Positions.getPosition(x, y);
        System.out.println("Clicked: " + x + " " + y);

        if (lastClick != null) {
            tokenLayer.moveToken(lastClick, currentClick);
            System.out.println("Move from: " + lastClick.getPositionX() + " " + lastClick.getPositionY() + " to: " + currentClick.getPositionX() + " " + currentClick.getPositionY());
        }

        lastClick = currentClick;
    }
}