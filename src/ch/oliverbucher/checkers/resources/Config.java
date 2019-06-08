package ch.oliverbucher.checkers.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {

    public static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle("ch.oliverbucher.checkers.resources.config");
    public static final int BOARD_WIDTH = getIntegerValue("BOARD_WIDTH");
    public static final int BOARD_HEIGHT = getIntegerValue("BOARD_HEIGHT");
    public static final int START_ROWS = getIntegerValue("START_ROWS");
    public static final double LENGTH_OF_SPACE =
            getDoubleValue("WINDOW_WIDTH") / getDoubleValue("BOARD_WIDTH") / 2;
    public static final String GAME_TITLE = getValue("GAME_TITLE");
    public static final double WINDOW_WIDTH = getDoubleValue("WINDOW_WIDTH");
    public static final double WINDOW_HEIGHT = getDoubleValue("WINDOW_HEIGHT");
    public static final String MSG_NO_OPPONENT = getValue("MSG_NO_OPPONENT");
    public static final String LINK_RULES = getValue("LINK_RULES");

    private static String getValue(String key) {

        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException exception) {
            return null;
        }
    }

    private static int getIntegerValue(String key) {

        String value = getValue(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    private static double getDoubleValue(String key) {

        String value = getValue(key);
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            return 0.0;
        }
    }

}
