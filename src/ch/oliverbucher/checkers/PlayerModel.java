package ch.oliverbucher.checkers;

public class PlayerModel {
    private String playerName;
    private PlayerType playerType;
    private PlayerColor playerColor;

    public PlayerModel(String playerName, PlayerType playerType, PlayerColor playerColor) {
        this.playerName = playerName;
        this.playerType = playerType;
        this.playerColor = playerColor;
    }
}
