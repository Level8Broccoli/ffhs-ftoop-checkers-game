package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;

public class KingPlayerToken extends PlayerToken {

    public KingPlayerToken(Player playerOwner, PositionXY positionXY) {

        super(playerOwner, positionXY);
    }
}
