package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;

public class PositionXY {

    private int positionX;
    private int positionY;

    public PositionXY(int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {

        return positionX;
    }

    public int getPositionY() {

        return positionY;
    }

    public boolean hasAllowedStartMovesOrJumps() {

        return MovesAndJumps.hasAllowedStartMovesOrJumps(this);
    }

    public boolean hasAllowedEndMovesOrJumps() {

        return MovesAndJumps.hasAllowedEndMovesOrJumps(this);
    }
}
