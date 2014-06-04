package chapter06;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recipe6_2 {
    Lock myLock = new ReentrantLock();

    public static void main(String[] args) {
        Recipe6_2 recipe = new Recipe6_2();
        recipe.start();
    }

    private void start() {
        for (int i = 0; i < 10; i++) {
            callFunctionThatHoldsLock();
        }
    }

    Random random = new Random();

    private void callFunctionThatHoldsLock() {
        myLock.lock();
        try {
            int number = random.nextInt(5);
            int result = 100/number;
            System.out.println("A result is " + number);
            FileOutputStream file = new FileOutputStream("file.out");
            file.write(result);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }
    }
}
