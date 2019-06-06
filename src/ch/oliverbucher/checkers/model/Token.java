package ch.oliverbucher.checkers.model;

public class Token {

    private Player playerOwner;
    private Position position;

    public Token(Position position, Player playerOwner) {

        this.playerOwner = playerOwner;
        this.position = position;
    }

    public Token(Position position) {

        this.playerOwner = null;
        this.position = position;
    }

    public Player getPlayerOwner() {
        return playerOwner;
    }

    public boolean playerIsAssigned() {

        return (playerOwner != null);
    }
}
