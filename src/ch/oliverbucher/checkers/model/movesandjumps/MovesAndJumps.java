package ch.oliverbucher.checkers.model.movesandjumps;

import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.*;

public class MovesAndJumps {

    private static List<AllowedMoveOrJump> allAllowedMovesOrJumps = new ArrayList<>();
    public static Map<PositionXY, AllowedMoveOrJump> endPositions = new HashMap<>();

    public static List<AllowedMoveOrJump> getAllAllowedMovesOrJumps() {

        return allAllowedMovesOrJumps;
    }

    public static void resetMoves() {

        allAllowedMovesOrJumps.clear();
    }

    public static void setMovesOrJumps(ArrayList<AllowedMoveOrJump> possibleMovesOrJumps) {

        allAllowedMovesOrJumps = possibleMovesOrJumps;
    }

    public static void deselectTokenToMove() {

        endPositions.clear();
    }

    public static Map<PositionXY, AllowedMoveOrJump> getEndPositionsFor(PositionXY currentClick) {

        Map<PositionXY, AllowedMoveOrJump> result = new HashMap<>();

        for (AllowedMoveOrJump allowedMoveOrJump: allAllowedMovesOrJumps) {
            if (allowedMoveOrJump.getStartPosition() == currentClick) {
                result.put(allowedMoveOrJump.getEndPosition(), allowedMoveOrJump);
            }
        }

        return result;
    }

    public static void selectTokenToMove(PositionXY currentClick) {
        endPositions = getEndPositionsFor(currentClick);
    }

    public static boolean isTokenSelected() {

        return endPositions != null && endPositions.size() > 0;
    }

    public static AllowedMoveOrJump getMoveOrJump(PositionXY currentClick) {

        return endPositions.get(currentClick);
    }
}
