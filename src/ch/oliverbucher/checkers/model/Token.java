package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;

public class Token implements TokenInterface {

    private Player playerOwner;
    private Position position;
    private PlayerColor playerColor;

    public Token(Player playerOwner, Position position, PlayerColor playerColor) {
        this.playerOwner = playerOwner;
        this.position = position;
        this.playerColor = playerColor;
    }

    @Override
    public Player getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
