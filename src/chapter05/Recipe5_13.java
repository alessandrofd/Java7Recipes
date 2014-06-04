package chapter05;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Recipe5_13 {
    public static void main(String[] args) {
        Recipe5_13 recipe = new Recipe5_13();
        recipe.start();
    }

    private void start() {
        ZipFile file = null;
        try {
            file = new ZipFile("file.zip");
            FileSystem fileSystem = FileSystems.getDefault();
            Enumeration<? extends ZipEntry> entries = file.entries();
            String uncompressedDirectory = "uncompressed/";
            Files.createDirectories(fileSystem.getPath(uncompressedDirectory));
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    System.out.println("Creating Directory: " + uncompressedDirectory + entry.getName());
                    Files.createDirectories(fileSystem.getPath((uncompressedDirectory + entry.getName())));
                } else {
                    InputStream is = file.getInputStream(entry);
                    System.out.println("File: " + entry.getName());
                    BufferedInputStream bis = new BufferedInputStream(is);

                    String uncompressedFileName = uncompressedDirectory + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                    Files.createFile(uncompressedFilePath);
                    FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                    while (bis.available() > 0) {
                        fileOutput.write(bis.read());
                    }
                    fileOutput.close();
                    System.out.println("Written: " + entry.getName());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
