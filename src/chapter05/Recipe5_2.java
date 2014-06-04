package chapter05;

import java.awt.*;
import java.io.*;

public class Recipe5_2 {

    public static void main(String[] args) {
        Recipe5_2 recipe = new Recipe5_2();
        recipe.start();
    }

    private void start() {
        ExternalizableProgramSettings settings =
            new ExternalizableProgramSettings(new Point(10,10), new Dimension(300,200), Color.blue, "The title of the application");
        saveSettings(settings, "settingsExternalizable.bin");
        ExternalizableProgramSettings loadedSettings = loadSettings("settingsExternalizable.bin");
        System.out.println("Are settings equal? " + loadedSettings.equals(settings));
    }

    private void saveSettings(ExternalizableProgramSettings settings, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExternalizableProgramSettings loadSettings(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ExternalizableProgramSettings) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
