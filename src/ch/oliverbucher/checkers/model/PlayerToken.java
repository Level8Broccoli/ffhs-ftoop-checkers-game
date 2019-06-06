package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;

import java.util.ArrayList;

public class PlayerToken extends Token {

    private Player playerOwner;
    private Position position;
    private ArrayList<Position> possibleMoves;

    public PlayerToken(Position position, Player playerOwner) {

        super(position);

        this.playerOwner = playerOwner;
    }

    public Player getPlayerOwner() {
        return playerOwner;
    }

    public boolean isPlayerAssigned() {

        return (playerOwner != null);
    }

    public void calculatePossibleMoves() {

        possibleMoves.clear();

        int currentPositionX = position.getPositionX();
        int currentPositionY = position.getPositionY();
        DirectionOfPlay directionOfPlay = playerOwner.getDirectionOfPlay();

        int nextRowY;

        if (directionOfPlay.isUp()) {
            nextRowY = currentPositionY - 1;
        } else {
            nextRowY = currentPositionY + 1;
        }

        Position possibleMoveLeft = new Position(currentPositionX - 1, nextRowY);
        if (possibleMoveLeft.isOnTheBoard() && possibleMoveLeft.isEmpty()) {
            possibleMoves.add(possibleMoveLeft);
        }

        Position possibleMoveRight = new Position(currentPositionX + 1, nextRowY);
        if (possibleMoveRight.isOnTheBoard() && possibleMoveRight.isEmpty()) {
            possibleMoves.add(possibleMoveRight);
        }
    }
}
