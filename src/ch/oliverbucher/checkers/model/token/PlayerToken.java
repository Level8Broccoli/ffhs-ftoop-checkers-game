package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

import java.util.ArrayList;

public class PlayerToken {

    private final Player playerOwner;
    private final DirectionOfPlay directionOfPlay;
    private ArrayList<PositionXY> allowedMoves;

    public PlayerToken(Player playerOwner) {

        this.playerOwner = playerOwner;
        this.directionOfPlay = playerOwner.getDirectionOfPlay();
    }

    public String getName() {

        return playerOwner.getPlayerColor().name();
    }

    public ArrayList<PositionXY> simpleMove(PositionXY currentPosition) {

        ArrayList<PositionXY> possibleMoves = new ArrayList<>();

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
            possibleMoves.add(possibleMoveLeft);
        }

        PositionXY possibleMoveRight = Positions.getPosition(currentPositionX + 1, nextRowY);
        if (possibleMoveRight != null) {
            possibleMoves.add(possibleMoveRight);
        }

        return possibleMoves;
    }

    public void setAllowedMoves(ArrayList<PositionXY> allowedMoves) {

        this.allowedMoves = allowedMoves;
    }

    public boolean hasAllowedMoves() {

        return allowedMoves != null && allowedMoves.size() > 0;
    }

    public ArrayList<PositionXY> getAllowedMoves() {

        return allowedMoves;
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }
}
