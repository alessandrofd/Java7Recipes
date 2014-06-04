package chapter05;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Alessandro on 19/04/2014.
 */
public class Recipe5_7 {
    public static void main(String[] args) {
        Recipe5_7 recipe = new Recipe5_7();
        recipe.moveFile();
    }

    private void moveFile() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path sourcePath = fileSystem.getPath("file.log");
        Path targetPath = fileSystem.getPath("fileMoved.log");
        System.out.println("Move from " + sourcePath.toAbsolutePath().toString() + " to " + targetPath.toAbsolutePath().toString());
        try {
            Files.move(sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
