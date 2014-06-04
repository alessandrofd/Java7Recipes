package chapter06;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class Recipe6_8 {
    public static void main(String[] args) {
        Recipe6_8 recipe = new Recipe6_8();
        recipe.start();
    }

    private void start() {
        try {
            doSomeWork();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSomeWork() throws IOException, InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        try {
            FileOutputStream fos = new FileOutputStream("out.log");
            DataOutputStream dos = new DataOutputStream(fos);
            while (!queue.isEmpty()) {
                dos.writeUTF(queue.take());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


