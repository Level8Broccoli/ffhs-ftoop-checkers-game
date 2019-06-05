package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;

public class Player {

    private PlayerType playerType;
    private PlayerColor playerColor;

    public Player(PlayerType playerType, PlayerColor playerColor) {

        this.playerType = playerType;
        this.playerColor = playerColor;
    }
}
