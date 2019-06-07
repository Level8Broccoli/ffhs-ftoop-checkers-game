package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.JumpDirections;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

import java.util.HashMap;

public class PlayerToken {

    private final Player playerOwner;
    private final DirectionOfPlay directionOfPlay;
    private HashMap<PositionXY, JumpDirections> allowedMoves;
    private HashMap<PositionXY, JumpDirections> allowedJumps;

    public PlayerToken(Player playerOwner) {

        this.playerOwner = playerOwner;
        this.directionOfPlay = playerOwner.getDirectionOfPlay();
    }

    public String getName() {

        return playerOwner.getPlayerColor().name();
    }

    public HashMap<PositionXY, JumpDirections> move(PositionXY currentPosition) {

        HashMap<PositionXY, JumpDirections> possibleMoves = new HashMap<>();

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
            possibleMoves.put(possibleMoveLeft, JumpDirections.LEFT);
        }

        PositionXY possibleMoveRight = Positions.getPosition(currentPositionX + 1, nextRowY);
        if (possibleMoveRight != null) {
            possibleMoves.put(possibleMoveRight, JumpDirections.RIGHT);
        }

        return possibleMoves;
    }

    public HashMap<PositionXY, JumpDirections> jumps(PositionXY currentPosition) {

        HashMap<PositionXY, JumpDirections> possibleJumps = new HashMap<>();

        int currentPositionX = currentPosition.getPositionX();
        int currentPositionY = currentPosition.getPositionY();
        int nextRowY;

        if (directionOfPlay.isUp()) {
            nextRowY = currentPositionY - 2;
        } else {
            nextRowY = currentPositionY + 2;
        }

        PositionXY possibleJumpLeft = Positions.getPosition(currentPositionX - 2, nextRowY);
        if (possibleJumpLeft != null) {
            possibleJumps.put(possibleJumpLeft, JumpDirections.LEFT);
        }

        PositionXY possibleJumpRight = Positions.getPosition(currentPositionX + 2, nextRowY);
        if (possibleJumpRight != null) {
            possibleJumps.put(possibleJumpRight, JumpDirections.LEFT);
        }

        return possibleJumps;
    }

    public void setAllowedMoves(HashMap<PositionXY, JumpDirections> allowedMoves) {

        this.allowedMoves = allowedMoves;
    }

    public void setAllowedJumps(HashMap<PositionXY, JumpDirections> allowedJumps) {

        this.allowedJumps = allowedJumps;
    }

    public boolean hasAllowedMoves() {

        return allowedMoves != null && allowedMoves.size() > 0;
    }

    public HashMap<PositionXY, JumpDirections> getAllowedMoves() {

        return allowedMoves;
    }

    public HashMap<PositionXY, JumpDirections> getAllowedJumps() {

        return allowedJumps;
    }

    public boolean isAllowedToMoveTo(PositionXY currentClick) {

        return allowedMoves.containsKey(currentClick);
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }
}
