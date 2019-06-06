package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;

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
        DirectionOfPlay directionOfPlay = playerOwner.getDirectionOfPlay();

        Position possibleMoveLeft = new Position(currentPositionX - 1, currentPositionY - 1);
        System.out.println(possibleMoveLeft.isOnTheBoard());

    }
}
