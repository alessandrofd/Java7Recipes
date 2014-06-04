package chapter05;

import java.awt.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Recipe5_3 {
    public static void main(String[] args) {
        Recipe5_3 recipe = new Recipe5_3();
        recipe.start();
    }

    private void start() {
        ProgramSettings settings =
            new ProgramSettings(new Point(10,10), new Dimension(300,200), Color.blue, "The title of the application");
        try {
            FileOutputStream fos = new FileOutputStream("settings.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception! " + e.toString());
                }
            });
            encoder.writeObject(settings);
            encoder.close();
            fos.close();

            FileInputStream fis = new FileInputStream("settings.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            ProgramSettings decodedSettings = (ProgramSettings) decoder.readObject();
            System.out.println("Is same? " + decodedSettings.equals(settings));
            decoder.close();
            fis.close();

            FileSystem fileSystem = FileSystems.getDefault();
            Path file = fileSystem.getPath("settings.xml");
            List<String> xmlLines = Files.readAllLines(file, Charset.defaultCharset());

            for (String line : xmlLines)
                System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
