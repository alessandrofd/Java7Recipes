package chapter06;

import java.util.Random;

public class Recipe6_5 {
    public static void main(String[] args) {
        Recipe6_5 recipe = new Recipe6_5();
        //recipe.start();
        recipe.startForCurrentThread();
    }

    private void start() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Woa! There was an exception thrown somewhere! " + t.getName() + ": " + e);
            }
        });

        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int divisor = random.nextInt(4);
            System.out.println("200 / " + divisor + " is " + (200/divisor));
        }
    }

    private void startForCurrentThread() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("In this thread " + t.getName() + " an exception was thrown " + e);
            }
        });
        Thread someThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(200/0);
            }
        });
        someThread.setName("Unlucky Thread");
        someThread.start();

        System.out.println("In the main thread " + 200/0);
    }
}
