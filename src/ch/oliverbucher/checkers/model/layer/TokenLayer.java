package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.players.Players;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class TokenLayer {

    private BoardLayer boardLayer;
    private HashMap<PositionXY, PlayerToken> tokens;

    public TokenLayer(BoardLayer boardLayer) {

        this.boardLayer = boardLayer;
        generateTokenLayer();
    }

    private void generateTokenLayer() {

        tokens = new HashMap<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                PositionXY currentPosition = Positions.getPosition(x, y);

                if (y < Config.START_ROWS) {

                    if (boardLayer.get(currentPosition).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(Players.getPlayer(1)));
                    }

                } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

                    if (boardLayer.get(currentPosition).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(Players.getPlayer(0)));
                    }
                }
            }
        }

        calculateAllPossibleMoves();
    }

    public void updateTokenLayer() {

        calculateAllPossibleMoves();
    }


    private void calculateAllPossibleMoves() {

        for (PositionXY currentPosition : tokens.keySet()) {

            PlayerToken currentToken = tokens.get(currentPosition);

            ArrayList<PositionXY> allowedMoves = new ArrayList<>();

            for (PositionXY possibleMovePosition : currentToken.simpleMove(currentPosition)) {

                if (tokens.get(possibleMovePosition) == null) {

                    allowedMoves.add(possibleMovePosition);
                }
            }

            currentToken.setAllowedMoves(allowedMoves);
        }
    }

    public PlayerToken get(PositionXY positionXY) {

        return tokens.get(positionXY);
    }

    public void moveToken(PositionXY lastClick, PositionXY currentClick) {

        PlayerToken activeToken = tokens.get(lastClick);
        System.out.println("Active Token: " + activeToken);
        tokens.remove(lastClick);
        tokens.put(currentClick, activeToken);
    }

    public HashMap<PositionXY, PlayerToken> getTokens() {

        return tokens;
    }
}
