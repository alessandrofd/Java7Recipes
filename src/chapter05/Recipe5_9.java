package chapter05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Recipe5_9 {
    public static void main(String[] args) {
        Recipe5_9 recipe = new Recipe5_9();
        recipe.traverseDirectory();
    }

    private void traverseDirectory() {
        FileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("Visited file " + file.toString());
                return FileVisitResult.CONTINUE;
            }
        };

        FileSystem fileSystem = FileSystems.getDefault();
        Path directory = fileSystem.getPath(".");
        try {
            Files.walkFileTree(directory, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
