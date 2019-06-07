package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;

import java.util.ArrayList;

public class PlayerToken {

    private final PositionXY position;
    private final Player playerOwner;
    private final DirectionOfPlay directionOfPlay;
    private ArrayList<PositionXY> possibleMoves;

    public PlayerToken(Player playerOwner, PositionXY position) {

        this.playerOwner = playerOwner;
        this.position = position;
        this.directionOfPlay = playerOwner.getDirectionOfPlay();
    }

    public void setPossibleMoves(ArrayList<PositionXY> possibleMoves) {

        this.possibleMoves = possibleMoves;
    }

    public boolean hasPossibleMoves() {

        return possibleMoves != null && possibleMoves.size() > 0;
    }

    public Player getPlayerOwner() {

        return playerOwner;
    }

    public String getName() {

        return playerOwner.getPlayerColor().name();
    }

    public ArrayList<PositionXY> simpleMove() {

        possibleMoves = new ArrayList<>();

        int currentPositionX = position.getPositionX();
        int currentPositionY = position.getPositionY();
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
}
