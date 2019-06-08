package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.layer.space.BoardSpace;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.resources.Config;
import java.util.HashMap;
import java.util.Map;

public class BoardLayer {

  private final Map<PositionXY, BoardSpace> boardLayer;
  private final Positions positions;

  public BoardLayer(Positions positions) {
    this.positions = positions;

    boardLayer = new HashMap<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {
      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        PositionXY currentPosition = positions.getPosition(x, y);

        BoardColor currentBoardColor;
        if ((x + y) % 2 == 0) {
          currentBoardColor = BoardColor.LIGHT;
        } else {
          currentBoardColor = BoardColor.DARK;
        }

        BoardSpace field = new BoardSpace(currentBoardColor);
        boardLayer.put(currentPosition, field);
      }
    }
  }

  public BoardSpace get(PositionXY currentPosition) {

    return boardLayer.get(currentPosition);
  }
}
