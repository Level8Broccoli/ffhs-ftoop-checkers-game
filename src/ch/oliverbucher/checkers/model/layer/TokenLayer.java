package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.HorizontalDirection;
import ch.oliverbucher.checkers.model.movesandjumps.MovesAndJumps;
import ch.oliverbucher.checkers.model.movesandjumps.AllowedMoveOrJump;
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

        calculateAllAllowedMovesAndJumps();
    }

    public void calculateAllAllowedMovesAndJumps() {

        MovesAndJumps.resetMoves();

        ArrayList<AllowedMoveOrJump> allowedMoves = new ArrayList<>();
        ArrayList<AllowedMoveOrJump> allowedJumps = new ArrayList<>();

        for (PositionXY currentPosition : tokens.keySet()) {

            PlayerToken currentToken = tokens.get(currentPosition);

            if (currentToken.getPlayerOwner() == Players.CURRENT_PLAYER) {

                HashMap<PositionXY, HorizontalDirection> possibleMoves = currentToken.getPossibleMoves(currentPosition);

                for (PositionXY possibleMovePosition : possibleMoves.keySet()) {

                    if (tokens.get(possibleMovePosition) == null) {

                        AllowedMoveOrJump possibleMove = new AllowedMoveOrJump(currentPosition, possibleMovePosition);
                        allowedMoves.add(possibleMove);

                    } else if (tokens.get(possibleMovePosition).getPlayerOwner() != Players.CURRENT_PLAYER) {


                        HorizontalDirection direction = possibleMoves.get(possibleMovePosition);

                        PositionXY positionBehindOpponent = currentToken.getPositionBehindOpponent(possibleMovePosition, direction);

                        if (positionBehindOpponent != null && tokens.get(positionBehindOpponent) == null) {
                            AllowedMoveOrJump possibleJump = new AllowedMoveOrJump(currentPosition,
                                    positionBehindOpponent, possibleMovePosition);
                            allowedJumps.add(possibleJump);
                        }
                    }
                }
            }
        }

        if (allowedJumps.size() == 0) {
            MovesAndJumps.setMovesOrJumps(allowedMoves);
        } else {
            MovesAndJumps.setMovesOrJumps(allowedJumps);
        }
    }

    public PlayerToken getTokenAt(PositionXY positionXY) {

        return tokens.get(positionXY);
    }

    public void executeMove(AllowedMoveOrJump currentMove) {

        PositionXY startPosition = currentMove.getStartPosition();
        PositionXY endPosition = currentMove.getEndPosition();
        PositionXY opponentToken = currentMove.getOpponentToken();

        if (opponentToken != null) {
            tokens.remove(opponentToken);
        }

        PlayerToken currentToken = tokens.get(startPosition);
        tokens.remove(startPosition);
        tokens.put(endPosition, currentToken);
    }
}
