package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;
import ch.oliverbucher.checkers.model.layer.TokenLayer;

import java.util.ArrayList;

public class Token {

    protected Position position;
    private ArrayList<Position> possibleMoves;

    public Token(Position position) {

        this.position = position;
    }

    public boolean calculatePossibleMoves(TokenLayer tokenLayer) {

        return false;
    }

    public boolean isPlayerAssigned() {

        return false;
    }

    public Player getPlayerOwner() {
        return null;
    }

    public boolean hasPossibleMoves() {

        return possibleMoves != null && possibleMoves.size() > 0;
    }
}