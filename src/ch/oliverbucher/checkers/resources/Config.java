package ch.oliverbucher.checkers.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {

    private static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle("ch.oliverbucher.checkers.resources.config");

    public static final int BOARD_WIDTH = getIntegerValue("BOARD_WIDTH");
    public static final int BOARD_HEIGHT = getIntegerValue("BOARD_HEIGHT");
    public static final int START_ROWS = getIntegerValue("START_ROWS");


    public static String getValue(String key) {

        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException exception) {
            return null;
        }
    }

    public static int getIntegerValue(String key) {

        String value = getValue(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    public static double getDoubleValue(String key) {

        String value = getValue(key);
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            return 0.0;
        }
    }

    public static ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }

    public static double LENGTH_OF_SPACE =
            getDoubleValue("WINDOW_WIDTH") / getDoubleValue("BOARD_WIDTH") / 2;
}
