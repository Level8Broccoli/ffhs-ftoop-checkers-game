package ch.oliverbucher.checkers.model;

public class Token implements TokenInterface {

    private Player playerOwner;
    private Position position;
    private Boolean isEmpty;

    public Token(Position position, Player playerOwner) {

        this.playerOwner = playerOwner;
        this.position = position;
        this.isEmpty = false;
    }

    public Token(Position position, Boolean isEmpty) {

        this.position = position;
        this.isEmpty = isEmpty;
    }

    @Override
    public Player getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
