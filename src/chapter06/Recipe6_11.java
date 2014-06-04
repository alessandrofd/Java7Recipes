package chapter06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Recipe6_11 {
    public static void main(String[] args) {
        Recipe6_11 recipe = new Recipe6_11();
        recipe.start();
    }

    static Logger rootLogger = LoggerFactory.getLogger("");

    private void start() {
        loadLoggingConfiguration();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                rootLogger.error("Error in thread " + t.getName() + " caused by " + e);
            }
        });

        int c = 20/0;
    }

    private void loadLoggingConfiguration() {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(new File("logging.properties"));
            LogManager.getLogManager().readConfiguration(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
