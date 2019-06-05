package ch.oliverbucher.checkers.resources;

import java.util.ResourceBundle;

public class Config {

    private static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle("ch.oliverbucher.checkers.resources.config");

    public static String getValue(String key) {

        return RESOURCE_BUNDLE.getString(key);
    }

    public static ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }
}
