package ch.oliverbucher.checkers.model.token;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import java.util.HashMap;

public class KingPlayerToken extends Token {

  KingPlayerToken(Player playerOwner) {
    super(playerOwner);
  }

  @Override
  public HashMap<PositionXY, HorizontalDirection> getPossibleMoves(PositionXY currentPosition) {
    return null;
  }
}
