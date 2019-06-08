package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.BoardColor;
import ch.oliverbucher.checkers.model.layer.space.BoardSpace;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.resources.Config;
import java.util.HashMap;

public class BoardLayer {

  private final HashMap<PositionXY, BoardSpace> boardLayer;

  public BoardLayer() {

    boardLayer = new HashMap<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {
      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        PositionXY currentPosition = Positions.getPosition(x, y);

        BoardColor currentBoardColor;
        Boolean isAllowed;
        if ((x + y) % 2 == 0) {
          currentBoardColor = BoardColor.LIGHT;
          isAllowed = false;
        } else {
          currentBoardColor = BoardColor.DARK;
          isAllowed = true;
        }

        BoardSpace field = new BoardSpace(currentBoardColor, isAllowed);
        boardLayer.put(currentPosition, field);
      }
    }
  }

  public BoardSpace get(PositionXY currentPosition) {

    return boardLayer.get(currentPosition);
  }
}
