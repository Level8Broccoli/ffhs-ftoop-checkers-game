package ch.oliverbucher.checkers.model;

import java.util.ArrayList;

public class Token {

    private Player playerOwner;
    private Position position;
    private ArrayList<Position> possibleMoves;

    public Token(Position position, Player playerOwner) {

        this.playerOwner = playerOwner;
        this.position = position;
    }

    public Token(Position position) {

        this.playerOwner = null;
        this.position = position;
    }

    public Player getPlayerOwner() {
        return playerOwner;
    }

    public boolean isPlayerAssigned() {

        return (playerOwner != null);
    }

    public void calculateAllPossibleMoves() {

        possibleMoves.clear();

        int currentPositionX = position.getPositionX();
        int currentPositionY = position.getPositionY();

    }
}
