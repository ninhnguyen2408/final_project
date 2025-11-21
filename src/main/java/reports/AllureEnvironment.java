package reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.ConfigData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AllureEnvironment {

    public static void setAllureEnvironment() {
        // Create environment.properties for Allure
        try {
            // Create directory if not exists
            File resultsDir = new File("target/allure-results");
            if (!resultsDir.exists()) {
                resultsDir.mkdirs();
            }

            FileWriter fileWriter = new FileWriter("target/allure-results/environment.properties");

            Properties properties = new Properties();
            properties.setProperty("Browser", "Chrome");
            properties.setProperty("Application.URL", ConfigData.URL);
            properties.setProperty("Java.Version", System.getProperty("java.version"));
            properties.setProperty("OS", System.getProperty("os.name"));
            properties.setProperty("OS.Version", System.getProperty("os.version"));
            properties.setProperty("Test.Environment", "QA");
            properties.setProperty("Selenium.Version", "4.25.0");
            properties.setProperty("TestNG.Version", "7.11.0");

            properties.store(fileWriter, "Environment Properties for Allure Report");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create executor.json for Allure
        createExecutorJson();
    }

    private static void createExecutorJson() {
        try {
            Map<String, Object> executor = new HashMap<>();
            executor.put("name", "Maven");
            executor.put("type", "maven");
            executor.put("url", "http://localhost");
            executor.put("buildOrder", System.currentTimeMillis());
            executor.put("buildName", "POS Selenium Tests #" + System.currentTimeMillis());
            executor.put("buildUrl", "http://localhost");
            executor.put("reportName", "POS Automation Test Report");
            executor.put("reportUrl", "http://localhost/report");

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("target/allure-results/executor.json"), executor);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}