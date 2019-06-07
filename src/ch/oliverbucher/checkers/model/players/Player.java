package ch.oliverbucher.checkers.model.players;

import ch.oliverbucher.checkers.enumaration.DirectionOfPlay;
import ch.oliverbucher.checkers.enumaration.PlayerColor;
import ch.oliverbucher.checkers.enumaration.PlayerType;

public class Player {

    private PlayerType playerType;
    private final PlayerColor playerColor;
    private final DirectionOfPlay directionOfPlay;

    public Player(PlayerType playerType, PlayerColor playerColor, DirectionOfPlay directionOfPlay) {

        this.playerType = playerType;
        this.playerColor = playerColor;
        this.directionOfPlay = directionOfPlay;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerColor getPlayerColor() {

        return playerColor;
    }

    public DirectionOfPlay getDirectionOfPlay() {

        return directionOfPlay;
    }
}
