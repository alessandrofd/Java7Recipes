package chapter08;

/**
 * Created by Alessandro on 30/03/2014.
 */
public class Recipe8_1 {

    public static void main(String[] args) {
        Recipe8_1 recipe = new Recipe8_1();
        recipe.start();
    }

    private void start() {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                doSomethingInBackground();
            }
        }, "Background Thread");

        System.out.println("Start");
        backgroundThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": is counting " + i);
        }
        System.out.println("Done.");
    }

    private void doSomethingInBackground() {
        System.out.println(Thread.currentThread().getName() +  ": is running in the background");
    }
}
