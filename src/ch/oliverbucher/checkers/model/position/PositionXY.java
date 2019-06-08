package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.resources.Config;

public class PositionXY {

  public final int positionX;
  public final int positionY;

  public PositionXY(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof PositionXY)) {
      return false;
    }

    PositionXY position = (PositionXY) obj;
    return positionX == position.positionX && positionY == position.positionY;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + positionX;
    result = 31 * result + positionY;
    return result;
  }

  public boolean isNotOutsideTheBoard() {

    return positionX >= 0 && positionY >= 0 && positionX < Config.BOARD_WIDTH && positionY < Config.BOARD_HEIGHT;

  }
}
