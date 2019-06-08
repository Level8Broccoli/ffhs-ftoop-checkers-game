package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.resources.Config;
import java.util.ArrayList;
import java.util.List;

public class Positions {

  private static List<List<PositionXY>> positions;

  private static void createPositions() {

    positions = new ArrayList<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {

      positions.add(new ArrayList<>());

      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        positions.get(x).add(new PositionXY(x, y));
      }
    }
  }

  public static PositionXY getPosition(int x, int y) {

    if (positions == null || positions.size() == 0) {
      Positions.createPositions();
    }

    if (x >= 0 && y >= 0 && x < Config.BOARD_WIDTH && y < Config.BOARD_HEIGHT) {

      return positions.get(x).get(y);
    }

    return null;
  }
}
