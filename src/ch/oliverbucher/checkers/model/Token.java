package ch.oliverbucher.checkers.model;

public class Token implements TokenInterface {

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

    @Override
    public Player getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean playerIsAssigned() {

        return (playerOwner != null);
    }
}
