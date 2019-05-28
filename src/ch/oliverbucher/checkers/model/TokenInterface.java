package ch.oliverbucher.checkers.model;

import ch.oliverbucher.checkers.enumaration.PlayerColor;

public interface TokenInterface {

    Player getPlayerOwner();
    int getCoordinateX();
    int getCoordinateY();
    PlayerColor getPlayerColor();
}
