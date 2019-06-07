package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.ArrayList;

public class PlayerToken {

    private final PositionXY positionXY;
    private final Player playerOwner;
    private ArrayList<PositionXY> possibleMoves;

    public PlayerToken(Player playerOwner, PositionXY positionXY) {

        this.playerOwner = playerOwner;
        this.positionXY = positionXY;
    }

    public void setPossibleMoves(ArrayList<PositionXY> possibleMoves) {

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
