package ch.oliverbucher.checkers.enumaration;

public enum PlayerColor {
  WHITE(DirectionOfPlay.UP),
  BLACK(DirectionOfPlay.DOWN);

  private final DirectionOfPlay direction;

  PlayerColor(DirectionOfPlay direction) {
    this.direction = direction;
  }

  public DirectionOfPlay getDirectionOfPlay() {
    return direction;
  }
}
