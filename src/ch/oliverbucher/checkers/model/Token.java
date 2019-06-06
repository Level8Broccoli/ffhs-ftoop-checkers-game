package ch.oliverbucher.checkers.model;

import java.util.ArrayList;

public class Token {

    private Position position;
    private ArrayList<Position> possibleMoves;

    public Token(Position position) {

        this.position = position;
    }

    public void calculatePossibleMoves() {

        possibleMoves.clear();
    }

    public boolean isPlayerAssigned() {

        return false;
    }

    public Player getPlayerOwner() {
        return null;
    }
}