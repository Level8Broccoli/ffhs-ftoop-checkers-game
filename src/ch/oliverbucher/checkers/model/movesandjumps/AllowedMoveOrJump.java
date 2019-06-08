package ch.oliverbucher.checkers.model.movesandjumps;

import ch.oliverbucher.checkers.model.position.PositionXY;

public class AllowedMoveOrJump {

  private final PositionXY startPosition;
  private final PositionXY endPosition;
  private final PositionXY jumpOverOpponentPosition;

  public AllowedMoveOrJump(PositionXY startPosition, PositionXY endPosition) {

    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.jumpOverOpponentPosition = null;
  }

  public AllowedMoveOrJump(
      PositionXY startPosition, PositionXY endPosition, PositionXY jumpOverOpponentPosition) {

    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.jumpOverOpponentPosition = jumpOverOpponentPosition;
  }

  public PositionXY getStartPosition() {

    return startPosition;
  }

  public PositionXY getEndPosition() {

    return endPosition;
  }

  public PositionXY getOpponentToken() {

    return jumpOverOpponentPosition;
  }
}
