package chapter08;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Alessandro on 30/03/2014.
 */
public class Recipe8_3 {

    public static void main(String[] args) {
        Recipe8_3 recipe = new Recipe8_3();
        recipe.start();
    }

    private void start() {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            startUpdateThread(i, concurrentMap);
        }
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Integer,String> entry : concurrentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }

    Random random = new Random();

    private void startUpdateThread(final int i, final ConcurrentMap<Integer,String> concurrentMap) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int randomInt = random.nextInt(20);
                String previousEntry = concurrentMap.putIfAbsent(randomInt, "Thread #" + i + " has made it!");
                if (previousEntry != null) {
                    System.out.println("Thread #" + i + " tried to update it but guess what, we're too late!");
                    return;
                } else {
                    System.out.println("Thread #" + i + " has made it!");
                }
            }
        });
        thread.start();
    }
}
