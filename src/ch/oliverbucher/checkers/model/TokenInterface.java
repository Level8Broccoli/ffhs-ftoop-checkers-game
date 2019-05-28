package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumarations.PlayerColor;

public interface TokenInterface {

    Player getPlayerOwner();
    int getCoordinateX();
    int getCoordinateY();
    PlayerColor getPlayerColor();
}
