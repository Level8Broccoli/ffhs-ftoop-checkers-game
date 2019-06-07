package ch.oliverbucher.checkers.model.movesandjumps;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.token.PlayerToken;

import java.util.*;

public class MovesAndJumps {

    private static List<AllowedMoveOrJump> allAllowedMovesOrJumps = new ArrayList<>();

    public static ArrayList<AllowedMoveOrJump> getAllAllowedMovesAndJumps(Map<PositionXY, PlayerToken> tokens) {

        ArrayList<AllowedMoveOrJump> allowedMoves = new ArrayList<>();
        ArrayList<AllowedMoveOrJump> allowedJumps = new ArrayList<>();

        for (PositionXY currentPosition : tokens.keySet()) {

            PlayerToken currentToken = tokens.get(currentPosition);

            if (currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {

                HashMap<PositionXY, HorizontalDirection> possibleMoves = currentToken.getPossibleMoves(currentPosition);

                for (PositionXY possibleMovePosition : possibleMoves.keySet()) {

                    if (tokens.get(possibleMovePosition) == null) {

                        AllowedMoveOrJump possibleMove = new AllowedMoveOrJump(currentPosition, possibleMovePosition);
                        allowedMoves.add(possibleMove);

                    } else if (tokens.get(possibleMovePosition).getPlayerOwner() != Players.CURRENT_PLAYER) {


                        HorizontalDirection direction = possibleMoves.get(possibleMovePosition);

                        PositionXY positionBehindOpponent = currentToken.getPositionBehindOpponent(possibleMovePosition, direction);

                        if (positionBehindOpponent != null && tokens.get(positionBehindOpponent) == null) {
                            AllowedMoveOrJump possibleJump = new AllowedMoveOrJump(currentPosition,
                                    positionBehindOpponent, possibleMovePosition);
                            allowedJumps.add(possibleJump);
                        }
                    }
                }
            }
        }

        if (allowedJumps.size() == 0) {
            return allowedMoves;
        } else {
            return allowedJumps;
        }
    }

    public static void setMovesOrJumps(ArrayList<AllowedMoveOrJump> possibleMovesOrJumps) {

        allAllowedMovesOrJumps = possibleMovesOrJumps;
    }


    public static Map<PositionXY, AllowedMoveOrJump> getEndPositionsFor(List<AllowedMoveOrJump> allAllowedMovesOrJumps, PositionXY currentClick) {

        Map<PositionXY, AllowedMoveOrJump> result = new HashMap<>();

        for (AllowedMoveOrJump allowedMoveOrJump: allAllowedMovesOrJumps) {
            if (allowedMoveOrJump.getStartPosition() == currentClick) {
                result.put(allowedMoveOrJump.getEndPosition(), allowedMoveOrJump);
            }
        }

        return result;
    }
}
