package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumarations.PlayerColor;
import ch.oliverbucher.checkers.enumarations.PlayerType;

public class Player {
    private String playerName;
    private PlayerType playerType;
    private PlayerColor playerColor;

    public Player(String playerName, PlayerType playerType, PlayerColor playerColor) {
        this.playerName = playerName;
        this.playerType = playerType;
        this.playerColor = playerColor;
    }
}
