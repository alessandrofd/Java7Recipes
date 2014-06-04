package chapter06;

import java.io.FileInputStream;
import java.io.IOException;

public class Recipe6_4 {
    public static void main(String[] args) {
        Recipe6_4 recipe = new Recipe6_4();
        recipe.start();
        recipe.startClassic();
    }

    private void startClassic() {
        try {
            Class<?> stringClass = Class.forName("java.lang.String");
            FileInputStream in = new FileInputStream("myFile.log"); // Can thrown IOException
            in.read();
        } catch (IOException e) {
            System.out.println("There was an IOException " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("There was a ClassNotFoundException " + e);
        }
    }

    private void start() {
        try {
            Class<?> stringClass = Class.forName("java.lang.String");
            FileInputStream in = new FileInputStream("myFileLog"); // Can throw IOException
            in.read();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An exception of type " + e.getClass() + " was thrown! " + e);
        }
    }
}
