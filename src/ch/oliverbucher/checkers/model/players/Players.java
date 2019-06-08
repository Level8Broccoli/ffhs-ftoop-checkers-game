package ch.oliverbucher.checkers.model.players;

import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;

public class Players {

    public final Player[] players =
        new Player[] {
            new Player(PlayerType.HUMAN, PlayerColor.WHITE),
            new Player(PlayerType.HUMAN, PlayerColor.BLACK)
        };
    public Player currentPlayer = players[0];

    public void nextPlayer() {
        currentPlayer = currentPlayer == players[0] ? players[1] : players[0];
    }
}
