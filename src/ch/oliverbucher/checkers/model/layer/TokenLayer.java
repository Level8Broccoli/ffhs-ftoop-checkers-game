package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.model.Player;
import ch.oliverbucher.checkers.model.Position;
import ch.oliverbucher.checkers.model.token.PlayerToken;
import ch.oliverbucher.checkers.model.token.Token;
import ch.oliverbucher.checkers.resources.Config;

import java.util.ArrayList;

public class TokenLayer {

    ArrayList<ArrayList<Token>> tokenLayer;

    public TokenLayer(BoardLayer boardLayer, Player[] players) {

        tokenLayer = new ArrayList<>();

        for (int x = 0; x < Config.BOARD_WIDTH; x++) {

            tokenLayer.add(new ArrayList<>());

            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
                if (y < Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new PlayerToken(new Position(x, y), players[1]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(x, y)));
                    }

                } else if (y >= Config.START_ROWS && y < Config.BOARD_HEIGHT - Config.START_ROWS) {

                    tokenLayer.get(x).add(new Token(new Position(x, y)));

                } else if (y >= Config.BOARD_HEIGHT - Config.START_ROWS) {

                    if (boardLayer.get(x).get(y).isAllowed()) {
                        tokenLayer.get(x).add(new PlayerToken(new Position(x, y), players[0]));
                    } else {
                        tokenLayer.get(x).add(new Token(new Position(x, y)));
                    }
                }
            }
        }
    }

    public ArrayList<Token> get(int x) {

        return tokenLayer.get(x);
    }

    public boolean isEmpty(Position position) {

        return true;
    }
}
