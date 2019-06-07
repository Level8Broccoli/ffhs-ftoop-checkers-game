package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;
import ch.oliverbucher.checkers.model.layer.BoardLayer;
import ch.oliverbucher.checkers.model.layer.MarkLayer;
import ch.oliverbucher.checkers.model.layer.TokenLayer;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;

public class CheckersGameModel {

    private final Player[] players = new Player[2];

    private final BoardLayer boardLayer;
    private TokenLayer tokenLayer;
    private MarkLayer markLayer;

    private PlayerToken activeToken;
    private PositionXY lastClick;

    public CheckersGameModel() {

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE, DirectionOfPlay.UP);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK, DirectionOfPlay.DOWN);

        boardLayer = new BoardLayer();
        tokenLayer = new TokenLayer(boardLayer, players);
        markLayer = new MarkLayer(tokenLayer);
    }

    public BoardLayer getBoardLayer() {

        return boardLayer;
    }

    public TokenLayer getTokenLayer() {

        return tokenLayer;
    }

    public MarkLayer getMarkLayer() {

        return markLayer;
    }

    public void setOpponent(PlayerType playerType) {

        players[1].setPlayerType(playerType);
    }

    public void clickEvent(int x, int y) {

        PositionXY currentClick = Positions.getPosition(x, y);
        markLayer.markCurrentClick(currentClick);
        PlayerToken currentToken = tokenLayer.get(currentClick);

        if (currentToken != null && activeToken == null) {
            activeToken = tokenLayer.get(currentClick);
            markLayer.showAllowedMoves(activeToken);
        } else if (activeToken != null){
            tokenLayer.moveToken(lastClick, currentClick);
            activeToken.updatePosition(currentClick);
            activeToken = null;
            tokenLayer.updateTokenLayer();
            markLayer.showTokensWithAllowedMoves();
        }

        lastClick = currentClick;
    }
}