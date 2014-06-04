package chapter03;

import java.util.Random;

/**
 * Created by Alessandro on 09/04/2014.
 */
public class Recipe3_08 {

    public static void main(String[] args) {
        randomExamples();
    }

    public static void randomExamples() {
        // Create a new instance of the Random class
        Random random = new Random();

        // Generates a random Integer
        int myInt = random.nextInt();

        // Generates a random Double value
        double myDouble = random.nextDouble();

        // Generates a random float
        float myFloat = random.nextFloat();

        // Generates a random Gaussian double mean 0.0 and standard deviation 1.0
        // from this random number generator's sequence
        double gausDouble = random.nextGaussian();

        // Generates a random long
        long myLong = random.nextLong();

        // Generates a random boolean
        boolean myBoolean = random.nextBoolean();


        double rand = Math.random();
    }
}
