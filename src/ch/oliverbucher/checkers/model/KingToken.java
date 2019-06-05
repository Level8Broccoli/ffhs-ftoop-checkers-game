package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;

public class KingToken extends Token implements TokenInterface {

    public KingToken(Player playerOwner, Position position, PlayerColor playerColor) {
        super(playerOwner, position, playerColor);
    }
}
