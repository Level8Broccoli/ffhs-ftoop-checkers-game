package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

import java.util.HashMap;

public class PlayerToken {

    private final Player playerOwner;

    public PlayerToken(Player playerOwner) {

        this.playerOwner = playerOwner;
    }

    public final String getName() {

        return playerOwner.getPlayerColor().name();
    }

    public HashMap<PositionXY, HorizontalDirection> getPossibleMoves(PositionXY currentPosition) {

        HashMap<PositionXY, HorizontalDirection> possibleMoves = new HashMap<>();

        final int currentPositionX = currentPosition.positionX;
        final int currentPositionY = currentPosition.positionY;
        final int nextRowY = playerOwner.getDirectionOfPlay().isUp() ? currentPositionY - 1 : currentPositionY + 1;

        final PositionXY possibleMoveLeft = Positions.getPosition(currentPositionX - 1, nextRowY);
        if (possibleMoveLeft != null) {
            possibleMoves.put(possibleMoveLeft, HorizontalDirection.LEFT);
        }

        final PositionXY possibleMoveRight = Positions.getPosition(currentPositionX + 1, nextRowY);
        if (possibleMoveRight != null) {
            possibleMoves.put(possibleMoveRight, HorizontalDirection.RIGHT);
        }

        return possibleMoves;
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }

    public PositionXY getPositionBehindOpponent(PositionXY jumpOverPosition, HorizontalDirection direction) {

        int targetPositionX = jumpOverPosition.positionX;
        int targetPositionY = jumpOverPosition.positionY;

        if (direction == HorizontalDirection.LEFT) {
            targetPositionX--;
        } else {
            targetPositionX++;
        }

        if (playerOwner.getDirectionOfPlay().isUp()) {
            targetPositionY--;
        } else {
            targetPositionY++;
        }

        return Positions.getPosition(targetPositionX, targetPositionY);
    }
}
