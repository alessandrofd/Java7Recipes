package chapter06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Recipe6_10 {
    public static void main(String[] args) {
        Recipe6_10 recipe = new Recipe6_10();
        recipe.start();
    }

    private void start() {
        loadLoggingConfiguration();

        Logger logger = LoggerFactory.getLogger("");
        logger.info("Logging for the first time.");
        logger.warn("A warning to be had.");
        logger.error("This is an error.");

        Logger rollingLogger = LoggerFactory.getLogger("rollingLogger");
        for (int i = 0; i < 5_000; i++) {
            rollingLogger.info("Logging for an event with: " + i);
        }
    }

    private void loadLoggingConfiguration() {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File("logging.properties"));
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
