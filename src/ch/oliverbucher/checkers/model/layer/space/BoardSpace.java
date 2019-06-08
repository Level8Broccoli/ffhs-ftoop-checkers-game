package ch.oliverbucher.checkers.model.layer.space;

import ch.oliverbucher.checkers.enumaration.BoardColor;

public class BoardSpace {

  private final BoardColor boardColor;

  public BoardSpace(BoardColor boardColor) {

    this.boardColor = boardColor;
  }

  public BoardColor getBoardColor() {

    return boardColor;
  }

  public Boolean isAllowed() {

    return boardColor == BoardColor.DARK;
  }
}
