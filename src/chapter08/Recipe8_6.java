package chapter08;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** In the following example, you create a BlockingQueue and fill it with Runnagle objects (which describe what needs
 * to be done). It then is passed to the ThreadPoolExecutor instance. The ThreadPoolExecutor is then initialized, and
 * started by calling the prestartAllCoreThreads() method and then you wait until all the Runnable objects are done
 * executing by calling the shutdown() method, followed by the awaitTermination() method:
 */
public class Recipe8_6 {

    public static void main(String[] args) throws InterruptedException {
        Recipe8_6 recipe = new Recipe8_6();
        recipe.start();
    }

    private void start() throws InterruptedException {
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
        for (int i = 0; i < 10; i++) {
            final int localI = i;
            queue.add(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(localI);
                }
            });
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        executor.shutdown();
        executor.awaitTermination(100_000, TimeUnit.SECONDS);

        System.out.println("Look ma! all operations completed");
    }

    private void doExpensiveOperation(int index) {
        System.out.println("Starting expensive operation " + index);
        try {
            Thread.sleep(index * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Ending expensive operation " + index);
    }
}
