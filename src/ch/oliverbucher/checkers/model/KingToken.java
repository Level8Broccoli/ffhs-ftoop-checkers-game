package ch.oliverbucher.checkers.model;

public class KingToken extends Token implements TokenInterface {

    public KingToken(Position position, Player playerOwner) {
        super(position, playerOwner);
    }
}
