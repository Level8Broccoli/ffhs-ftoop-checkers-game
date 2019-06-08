package ch.oliverbucher.checkers.enumaration;

public enum DirectionOfPlay {
  DOWN,
  UP;

  public boolean isUp() {

    return (this == UP);
  }
}
