package chapter05;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class Recipe5_11 {
    public static void main(String[] args) {
        Recipe5_11 recipe = new Recipe5_11();
        recipe.start();
    }

    private void start() {
        try {
            System.out.println("Watch Event, press q<Enter> to exit");
            FileSystem fileSystem = FileSystems.getDefault();
            WatchService service = fileSystem.newWatchService();
            Path path = fileSystem.getPath(".");
            System.out.println("Watching: " + path.toAbsolutePath());
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            boolean shouldContinue = true;
            while(shouldContinue) {
                WatchKey key = service.poll(250, TimeUnit.MILLISECONDS);

                // Code to stop the program
                while (System.in.available() > 0) {
                    int readChar = System.in.read();
                    if ((readChar == 'q' || readChar == 'Q')) {
                        shouldContinue = false;
                        break;
                    }
                }
                if (key == null) continue;
                for(WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) continue;
                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path filename = ev.context();
                    System.out.println("Event detected: " + filename.toString() + " "  + ev.kind());
                }

                boolean valid = key.reset();
                if (!valid)
                    break;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
