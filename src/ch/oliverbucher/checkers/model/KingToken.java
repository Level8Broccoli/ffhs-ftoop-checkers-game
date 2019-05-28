package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;

public class KingToken extends Token implements TokenInterface {

    public KingToken(Player playerOwner, int coordinateX, int coordinateY, PlayerColor playerColor) {
        super(playerOwner, coordinateX, coordinateY, playerColor);
    }
}
