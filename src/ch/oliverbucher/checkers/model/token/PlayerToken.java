package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;
import ch.oliverbucher.checkers.model.layer.TokenLayer;

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

    public boolean calculatePossibleMoves(TokenLayer tokenLayer) {

        if (possibleMoves == null || possibleMoves.size() == 0) {
            possibleMoves = new ArrayList<>();
        }

        int currentPositionX = super.position.getPositionX();
        int currentPositionY = super.position.getPositionY();
        DirectionOfPlay directionOfPlay = playerOwner.getDirectionOfPlay();

        int nextRowY;

        if (directionOfPlay.isUp()) {
            nextRowY = currentPositionY - 1;
        } else {
            nextRowY = currentPositionY + 1;
        }

        Position possibleMoveLeft = new Position(currentPositionX - 1, nextRowY);

        System.out.println(possibleMoveLeft);
        if (possibleMoveLeft.isOnTheBoard() && tokenLayer.isEmpty(possibleMoveLeft)) {
            possibleMoves.add(possibleMoveLeft);
        }

        Position possibleMoveRight = new Position(currentPositionX + 1, nextRowY);
        if (possibleMoveRight.isOnTheBoard() && tokenLayer.isEmpty(possibleMoveRight)) {
            possibleMoves.add(possibleMoveRight);
        }

        if (possibleMoves != null && possibleMoves.size() > 0) {

            System.out.println("Token: " + currentPositionX + currentPositionY + " has " + possibleMoves.size() + " " +
                    "possible moves");
            return true;
        }
        return false;
    }
}
