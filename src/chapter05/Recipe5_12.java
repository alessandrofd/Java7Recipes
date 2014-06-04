package chapter05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Recipe5_12 {
    public static void main(String[] args) {
        Recipe5_12 recipe = new Recipe5_12();
        recipe.start();
    }

    private void start() {
        // Create property file, at least
        File file = new File("properties.conf");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties.conf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean shouldWakeUp = false;
        String shouldWakeUpProperty = properties.getProperty("shouldWakeUp");
        shouldWakeUp = (shouldWakeUpProperty == null) ? false : Boolean.parseBoolean(shouldWakeUpProperty.trim().toLowerCase());

        int startCounter = 100;
        String startCounterString = properties.getProperty("startCounter");
        try {
            startCounter = Integer.parseInt(startCounterString);
        } catch (Exception e) {
            System.out.println("Couldn't read startCounter, defaulting to: " + startCounter);
        }

        String dateFormatStringProperty = properties.getProperty("dateFormatString", "MMM dd yy");

        System.out.println("Should Wake Up? " + shouldWakeUp);
        System.out.println("Start Counter: " + startCounter);
        System.out.println("Date Format String: " + dateFormatStringProperty);

        // Setting properties
        properties.setProperty("startCounter", "250");
        try {
            properties.store(new FileOutputStream("properties.conf"), "Properties Description");
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list(System.out);
    }
}
