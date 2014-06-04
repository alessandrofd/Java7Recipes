package chapter08;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Alessandro on 30/03/2014.
 */
public class Recipe8_2 {

    public static void main(String[] args) {
        Recipe8_2 recipe = new Recipe8_2();
        recipe.start();
    }

    Set<Thread> updateThreads = new HashSet<Thread>();

    private void start() {
        ConcurrentMap<Integer,String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 1_000; i++) {
            startUpdateThread(i, concurrentMap);
        }
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Integer,String> entry: concurrentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
        for (Thread threads: updateThreads) {
            threads.interrupt();
        }
    }

    Random random = new Random();

    private void startUpdateThread(int i, ConcurrentMap<Integer,String> concurrentMap) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    int randomInt = random.nextInt(20);
                    concurrentMap.put(randomInt, UUID.randomUUID().toString());
                }
            }
        });
        thread.setName("Update Thread " + i);
        updateThreads.add(thread);
        thread.start();
    }
}
