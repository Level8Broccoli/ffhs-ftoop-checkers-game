package ch.oliverbucher.checkers.enumaration;

public enum MarkType {
  CURRENT_CLICK,
  POSSIBLE_MOVE,
  TOKEN_COULD_MOVE_OR_JUMP;

  public String getName() {

    return this.name();
  }
}
