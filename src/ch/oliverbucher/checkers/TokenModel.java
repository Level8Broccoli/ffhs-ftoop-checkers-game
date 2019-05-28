package ch.oliverbucher.checkers;

public class TokenModel implements TokenModelInterface {

    PlayerModel playerOwner;
    int coordinateX;
    int coordinateY;
    PlayerColor playerColor;

    public TokenModel(PlayerModel playerOwner, int coordinateX, int coordinateY, PlayerColor playerColor) {
        this.playerOwner = playerOwner;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.playerColor = playerColor;
    }

    @Override
    public PlayerModel getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public int getCoordinateX() {
        return coordinateX;
    }

    @Override
    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
