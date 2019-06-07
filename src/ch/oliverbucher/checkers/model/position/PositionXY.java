package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.resources.Config;

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

    public boolean isOnTheBoard() {

        return positionX >= 0 &&
                positionY >= 0 &&
                positionX < Config.BOARD_WIDTH &&
                positionY < Config.BOARD_HEIGHT;
    }
}
