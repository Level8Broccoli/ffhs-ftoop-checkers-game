package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

import java.util.HashMap;

public class PlayerToken {

    private final Player playerOwner;
    private final DirectionOfPlay directionOfPlay;

    public PlayerToken(Player playerOwner) {

        this.playerOwner = playerOwner;
        this.directionOfPlay = playerOwner.getDirectionOfPlay();
    }

    public String getName() {

        return playerOwner.getPlayerColor().name();
    }

    public HashMap<PositionXY, HorizontalDirection> getPossibleMoves(PositionXY currentPosition) {

        HashMap<PositionXY, HorizontalDirection> possibleMoves = new HashMap<>();

        int currentPositionX = currentPosition.getPositionX();
        int currentPositionY = currentPosition.getPositionY();
        int nextRowY;

        if (directionOfPlay.isUp()) {
            nextRowY = currentPositionY - 1;
        } else {
            nextRowY = currentPositionY + 1;
        }

        PositionXY possibleMoveLeft = Positions.getPosition(currentPositionX - 1, nextRowY);
        if (possibleMoveLeft != null) {
            possibleMoves.put(possibleMoveLeft, HorizontalDirection.LEFT);
        }

        PositionXY possibleMoveRight = Positions.getPosition(currentPositionX + 1, nextRowY);
        if (possibleMoveRight != null) {
            possibleMoves.put(possibleMoveRight, HorizontalDirection.RIGHT);
        }

        return possibleMoves;
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }

    public PositionXY getPositionBehindOpponent(PositionXY jumpOverPosition, HorizontalDirection direction) {

        int targetPositionX = jumpOverPosition.getPositionX();
        int targetPositionY = jumpOverPosition.getPositionY();

        if (direction == HorizontalDirection.LEFT) {
            targetPositionX--;
        } else {
            targetPositionX++;
        }

        if (directionOfPlay.isUp()) {
            targetPositionY--;
        } else {
            targetPositionY++;
        }

        return Positions.getPosition(targetPositionX, targetPositionY);
    }
}
