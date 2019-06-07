package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.resources.Config;

import java.util.HashMap;

public class TokenLayer {

    private Player[] players;
    private BoardLayer boardLayer;
    private HashMap<Position, PlayerToken> tokens;

    public TokenLayer(BoardLayer boardLayer, Player[] players) {

        this.boardLayer = boardLayer;
        this.players = players;
        generateTokenLayer();
    }

    public void generateTokenLayer() {

        tokens = new HashMap<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {

                Position currentPosition = boardLayer.get(x).get(y).getPosition();

                if (y < Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(players[1], currentPosition));
                    }

                } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {

                        tokens.put(currentPosition, new PlayerToken(players[0], currentPosition));
                    }
                }
            }
        }

        calculateAllPossibleMoves();
    }

    public void calculateAllPossibleMoves() {

        for (Position position: tokens.keySet()) {
            System.out.println("Token on " + position.getPositionX() + " " + position.getPositionY() + ", belonging " +
                    "to " + tokens.get(position).getPlayerOwner().getPlayerColor().name());
        }
//
//        for (int x = 0; x < Config.BOARD_WIDTH; x++) {
//
//            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
//
//                tokenLayer.get(new Position(x, y)).calculatePossibleMoves(this);
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
//        Position possibleMoveLeft = new Position(currentPositionX - 1, nextRowY);
//        if (possibleMoveLeft.isOnTheBoard() && tokenLayer.isEmpty(possibleMoveLeft)) {
//            possibleMoves.add(possibleMoveLeft);
//        }
//
//        Position possibleMoveRight = new Position(currentPositionX + 1, nextRowY);
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

    public PlayerToken get(Position position) {

        return tokens.get(position);
    }

//    public boolean isEmpty(Position position) {
//
//        return !tokenLayer.get(position.getPositionX()).get(position.getPositionY()).isPlayerAssigned();
//    }
}
