package chapter05;

import java.io.IOException;
import java.nio.file.*;

public class Recipe5_6 {
    public static void main(String[] args) {
        Recipe5_6 recipe = new Recipe5_6();
        recipe.copyFile();
    }

    private void copyFile() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path sourcePath = fileSystem.getPath("file.log");
        Path targetPath = fileSystem.getPath("file2.log");
        System.out.println("Copy from " + sourcePath.toAbsolutePath().toString() + " to " + targetPath.toAbsolutePath().toString());
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






