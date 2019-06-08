package ch.oliverbucher.checkers.model.position;

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
}
