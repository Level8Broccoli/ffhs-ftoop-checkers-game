package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;

public class KingPlayerToken extends PlayerToken {

    public KingPlayerToken(Position position, Player playerOwner) {

        super(position, playerOwner);
    }
}
