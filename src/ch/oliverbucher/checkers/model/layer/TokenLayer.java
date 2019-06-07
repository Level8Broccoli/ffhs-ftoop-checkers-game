package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.position.PositionXY;
import ch.oliverbucher.checkers.model.position.Positions;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class TokenLayer {

    private Player[] players;
    private BoardLayer boardLayer;
    private HashMap<PositionXY, PlayerToken> tokens;

    public TokenLayer(BoardLayer boardLayer, Player[] players) {

        this.boardLayer = boardLayer;
        this.players = players;
        generateTokenLayer();
    }

    public void generateTokenLayer() {

        tokens = new HashMap<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                PositionXY currentPosition = Positions.getPosition(x, y);

                if (y < Config.START_ROWS) {

                    if (boardLayer.get(currentPosition).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(players[1], currentPosition));
                    }

                } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

                    if (boardLayer.get(currentPosition).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(players[0], currentPosition));
                    }
                }
            }
        }

        calculateAllPossibleMoves();
    }

    public void calculateAllPossibleMoves() {

        for (PositionXY currentPosition : tokens.keySet()) {

            PlayerToken currentToken = tokens.get(currentPosition);
            Player currentTokenPlayerOwner = currentToken.getPlayerOwner();

            ArrayList<PositionXY> allowedMoves = new ArrayList<>();

            for (PositionXY possibleMovePosition : currentToken.simpleMove()) {

                if (tokens.get(possibleMovePosition) == null) {

                    allowedMoves.add(possibleMovePosition);
                }
            }
        }
//
//        for (int x = 0; x < Config.BOARD_WIDTH; x++) {
//
//            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
//
//                tokenLayer.get(new PositionXY(x, y)).calculatePossibleMoves(this);
//            }
//        }
//
//    public void calculatePossibleMoves(int currentPositionX, int currentPositionY) {
//
//        if (possibleMoves == null || possibleMoves.size() == 0) {
//            possibleMoves = new ArrayList<>();
//        }
//
//        DirectionOfPlay directionOfPlay = playerOwner.getDirectionOfPlay();
//
//        int nextRowY;
//
//        if (directionOfPlay.isUp()) {
//            nextRowY = currentPositionY - 1;
//        } else {
//            nextRowY = currentPositionY + 1;
//        }
//
//        PositionXY possibleMoveLeft = new PositionXY(currentPositionX - 1, nextRowY);
//        if (possibleMoveLeft.isOnTheBoard() && tokenLayer.isEmpty(possibleMoveLeft)) {
//            possibleMoves.add(possibleMoveLeft);
//        }
//
//        PositionXY possibleMoveRight = new PositionXY(currentPositionX + 1, nextRowY);
//        if (possibleMoveRight.isOnTheBoard() && tokenLayer.isEmpty(possibleMoveRight)) {
//            possibleMoves.add(possibleMoveRight);
//        }
//
//        if (possibleMoves != null && possibleMoves.size() > 0) {
//
////            TODO Delete sout
//            System.out.println("Token: " + currentPositionX + currentPositionY + " has " + possibleMoves.size() + " " +
//                    "possible moves");
//        }
//    }

    }

    public PlayerToken get(PositionXY positionXY) {

        return tokens.get(positionXY);
    }

    public void moveToken(PositionXY lastClick, PositionXY currentClick) {

        PlayerToken activeToken = tokens.get(lastClick);
        tokens.remove(lastClick);
        tokens.put(currentClick, activeToken);
    }

//    public boolean isEmpty(PositionXY position) {
//
//        return !tokenLayer.get(position.getPositionX()).get(position.getPositionY()).isPlayerAssigned();
//    }
}
