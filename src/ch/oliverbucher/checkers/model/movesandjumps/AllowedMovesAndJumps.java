package ch.oliverbucher.checkers.model.movesandjumps;

import java.util.ArrayList;
import java.util.List;

public class AllowedMovesAndJumps {
    public final List<AllowedMoveOrJump> allowedMoves = new ArrayList<>();
    public final List<AllowedMoveOrJump> allowedJumps = new ArrayList<>();

    public List<AllowedMoveOrJump> getMoreImportantMoves() {
        if (allowedJumps.size() == 0) {
            return allowedMoves;
        } else {
            return allowedJumps;
        }
    }
}
