package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;

public class Token implements TokenInterface {

    private Player playerOwner;
    private int coordinateX;
    private int coordinateY;
    private PlayerColor playerColor;

    public Token(Player playerOwner, int coordinateX, int coordinateY, PlayerColor playerColor) {
        this.playerOwner = playerOwner;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.playerColor = playerColor;
    }

    @Override
    public Player getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public int getCoordinateX() {
        return coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
