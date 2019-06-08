package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.resources.Config;
import java.util.ArrayList;
import java.util.List;

public class Positions {

  private final List<List<PositionXY>> positions;

  public Positions() {

    positions = new ArrayList<>();

    for (int x = 0; x < Config.BOARD_WIDTH; x++) {

      positions.add(new ArrayList<>());

      for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

        positions.get(x).add(new PositionXY(x, y));
      }
    }
  }

  public PositionXY getPosition(int x, int y) {

    if (x >= 0 && y >= 0 && x < Config.BOARD_WIDTH && y < Config.BOARD_HEIGHT) {

      return positions.get(x).get(y);
    }

    return null;
  }
}
