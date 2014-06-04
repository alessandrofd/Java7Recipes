package chapter06;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Recipe6_6 {
    public static void main(String[] args) {
        Recipe6_6 recipe = new Recipe6_6();
        recipe.start();
    }

    private void start() {
        try (
                FileOutputStream fos = new FileOutputStream("out.log");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos);
        ) {
            dos.writeUTF("This is being written");
        } catch (Exception e) {
            System.out.println("Something bad happened.");
        }
    }
}
