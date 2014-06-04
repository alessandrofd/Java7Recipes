package chapter05;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Recipe5_8 {
    public static void main(String[] args) {
        Recipe5_8 recipe = new Recipe5_8();
        recipe.createDirectory();
    }

    private void createDirectory() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path directory = fileSystem.getPath("./Novo Diretório");
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
