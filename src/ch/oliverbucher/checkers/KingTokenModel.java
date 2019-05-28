package ch.oliverbucher.checkers;

public class KingTokenModel extends TokenModel implements TokenModelInterface {

    public KingTokenModel(PlayerModel playerOwner, int coordinateX, int coordinateY, PlayerColor playerColor) {
        super(playerOwner, coordinateX, coordinateY, playerColor);
    }
}
