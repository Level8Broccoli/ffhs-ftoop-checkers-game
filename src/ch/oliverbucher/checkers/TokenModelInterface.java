package ch.oliverbucher.checkers;

public interface TokenModelInterface {

    PlayerModel getPlayerOwner();
    int getCoordinateX();
    int getCoordinateY();
    PlayerColor getPlayerColor();
}
