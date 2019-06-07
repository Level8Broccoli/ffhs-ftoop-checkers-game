package ch.oliverbucher.checkers.model.movesandjumps;

import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.ArrayList;
import java.util.HashMap;

public class MovesAndJumps {

    private static ArrayList<AllowedMoveOrJump> allPossibleMovesOrJumps = new ArrayList<>();
    private static HashMap<PositionXY, AllowedMoveOrJump> endPositions = new HashMap<>();

    public static ArrayList<AllowedMoveOrJump> getAllPossibleMovesOrJumps() {

        return allPossibleMovesOrJumps;
    }

    public static void resetMoves() {

        allPossibleMovesOrJumps.clear();
    }

    public static void setMoveOrJump(AllowedMoveOrJump allowedMoveOrJump) {

        allPossibleMovesOrJumps.add(allowedMoveOrJump);
    }

    public static void setMovesOrJumps(ArrayList<AllowedMoveOrJump> possibleMovesOrJumps) {

        allPossibleMovesOrJumps.addAll(possibleMovesOrJumps);
    }

    public static boolean hasAllowedStartMovesOrJumps(PositionXY position) {

        boolean checkValue = false;

        for (AllowedMoveOrJump allowedMoveOrJump: allPossibleMovesOrJumps) {
            if (allowedMoveOrJump.getStartPosition() == position) {
                checkValue = true;
            }
        }

        return checkValue;
    }

    public static boolean hasAllowedEndMovesOrJumps(PositionXY position) {

        boolean checkValue = false;

        for (AllowedMoveOrJump allowedMoveOrJump: allPossibleMovesOrJumps) {
            if (allowedMoveOrJump.getEndPosition() == position) {
                checkValue = true;
            }
        }

        return checkValue;
    }

    public static void resetEndPositions() {

        endPositions.clear();
        System.out.println("end positions removed");
    }

    public static void setEndPositions(PositionXY currentClick) {

        resetEndPositions();

        for (AllowedMoveOrJump allowedMoveOrJump: allPossibleMovesOrJumps) {
            if (allowedMoveOrJump.getStartPosition() == currentClick) {
                endPositions.put(allowedMoveOrJump.getEndPosition(), allowedMoveOrJump);
            }
        }
        System.out.println("setting end positions: " + endPositions);
    }

    public static boolean areEndPositionsSet() {

        return endPositions != null || endPositions.size() > 0;
    }

    public static AllowedMoveOrJump getMoveOrJump(PositionXY currentClick) {

        return endPositions.get(currentClick);
    }
}
