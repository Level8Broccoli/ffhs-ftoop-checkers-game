package ch.oliverbucher.checkers.model.position;

import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;

public class Positions {

    private static Positions ourInstance = new Positions();
    private static ArrayList<ArrayList<PositionXY>> positions;

    public Positions() {

        positions = new ArrayList<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            positions.add(new ArrayList<>());

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                positions.get(x).add(new PositionXY(x, y));
            }
        }
    }

    public static Positions getInstance() {

        return ourInstance;
    }

    public static PositionXY getPosition(int x, int y) {

        return positions.get(x).get(y);
    }
}
