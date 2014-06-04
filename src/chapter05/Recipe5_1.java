package chapter05;


import java.awt.*;
import java.io.*;

public class Recipe5_1 {
    public static void main(String[] args) {
        Recipe5_1 recipe = new Recipe5_1();
        recipe.start();
    }

    private void start() {
        ProgramSettings settings = new ProgramSettings(new Point(10,10), new Dimension(300,200), Color.blue, "The Tiltle of the application");
        saveSettings(settings, "settings.bin");
        ProgramSettings loadedSettings = loadSettings("settings.bin");
        System.out.println("Are settings equal? " + loadedSettings.equals(settings));
    }

    private void saveSettings(ProgramSettings settings, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ProgramSettings loadSettings(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ProgramSettings) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
