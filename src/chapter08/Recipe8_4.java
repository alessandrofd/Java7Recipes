package chapter08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Alessandro on 30/03/2014.
 */
public class Recipe8_4 {
    public static void main(String[] args) {
        Recipe8_4 recipe = new Recipe8_4();
        recipe.start();
    }

    public void start() {
        System.out.println("Using CopyOnWrite");
        copyOnWriteSolution();
        System.out.println("Using synchronizedList()");
        synchronizedListSolution();
    }

    public void copyOnWriteSolution() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        startUpdatingThread(list);
        for (String element : list) {
            System.out.println("Element: " + element);
        }
        stopUpdatingThread();
    }

    public void synchronizedListSolution() {
        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        startUpdatingThread(list);
        synchronized (list) {
            for (String element: list) {
                System.out.println("Element: " + element);
            }
        }
        stopUpdatingThread();
    }

    Thread updatingThread;
    Random random = new Random();

    public void startUpdatingThread(final List<String> list) {
        updatingThread = new Thread(new Runnable() {
            long counter = 0;
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    int size = list.size();
                    if (random.nextBoolean()) {
                        if (size > 1) {
                            list.remove(random.nextInt(size - 1));
                        } else if (size < 20) {
                            list.add("Random string " + counter);
                        }
                    }
                    counter++;
                }
            }
        });
        updatingThread.start();

        // let it warm up for a second
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void stopUpdatingThread() {
        updatingThread.interrupt();
    }
}
