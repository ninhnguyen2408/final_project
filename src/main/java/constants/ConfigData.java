package constants;

import helpers.PropertiesHelper;

public class ConfigData {
    static {
        PropertiesHelper.loadAllFiles();
    }

    public static String URL = PropertiesHelper.getValue("URL");
    public static String Email = PropertiesHelper.getValue("EMAIL");
    public static String Password = PropertiesHelper.getValue("PASSWORD");

    // Invalid test data
    public static String InvalidEmail = PropertiesHelper.getValue("INVALID_EMAIL");
    public static String InvalidPassword = PropertiesHelper.getValue("INVALID_PASSWORD");

    private ConfigData() {
        // Prevent instantiation
    }
}
