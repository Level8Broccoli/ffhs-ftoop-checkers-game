package ch.oliverbucher.checkers.model.layer.space;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace {

  private final boolean isAllowed;
  private final BoardColor boardColor;

  public BoardSpace(BoardColor boardColor, Boolean isAllowed) {

    this.boardColor = boardColor;
    this.isAllowed = isAllowed;
  }

  public BoardColor getBoardColor() {

    return boardColor;
  }

  public Boolean isAllowed() {

    return isAllowed;
  }
}
