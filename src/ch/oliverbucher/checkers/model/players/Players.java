package ch.oliverbucher.checkers.model.players;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;

public class Players {

    private static final Player[] players = new Player[2];
    public static Player CURRENT_PLAYER;

    public static void initializePlayers() {

        players[0] = new Player(PlayerType.HUMAN, PlayerColor.WHITE, DirectionOfPlay.UP);
        players[1] = new Player(PlayerType.HUMAN, PlayerColor.BLACK, DirectionOfPlay.DOWN);

        CURRENT_PLAYER = players[0];
    }

    public static Player getPlayer(int i) {

        return players[i];
    }

    public static void nextPlayer() {

        if (CURRENT_PLAYER == players[0]) {
            CURRENT_PLAYER = players[1];
        } else {
            CURRENT_PLAYER = players[0];
        }
    }
}
