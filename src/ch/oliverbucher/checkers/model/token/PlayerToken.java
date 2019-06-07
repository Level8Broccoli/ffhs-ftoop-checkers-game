package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;

import java.util.ArrayList;

public class PlayerToken {

    private final Position position;
    private final Player playerOwner;
    private ArrayList<Position> possibleMoves;

    public PlayerToken(Player playerOwner, Position position) {

        this.playerOwner = playerOwner;
        this.position = position;
    }

    public void setPossibleMoves(ArrayList<Position> possibleMoves) {

        this.possibleMoves = possibleMoves;
    }

    public boolean hasPossibleMoves() {

        return possibleMoves != null && possibleMoves.size() > 0;
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }

    public String getName() {

        return playerOwner.getPlayerColor().name();
    }
}
