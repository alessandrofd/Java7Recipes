import org.apache.commons.math3.complex.Complex;

import java.util.List;


public class Recipe3_05 {
    public static void main(String[] args) {
        complexArithmetic();
    }

    public static void  complexArithmetic() {

        // Create complex numbers by passing to floats to the Complex class
        Complex complex1 = new Complex(8.0, 3.0);
        Complex complex2 = new Complex(4.2, 5.0);
        Complex complex3 = new Complex(8.7, 13.53);
        Complex result;

        // Find the absolute value of a complex number
        double absresult = complex1.abs();

        // Compute the exponential function
        Complex exp = complex1.exp();

        // Add two complex numbers together
        result = complex1.add(complex2);

        // Subtract two complex numbers
        result = complex2.subtract(complex3);

        // Divide two complex numbers
        result = complex2.divide(complex3);

        // Multiply two complex numbers
        result = complex1.multiply(complex2);

        // Multiply a complex number and a double
        result = complex1.multiply(absresult);


        // Return the additive inverse of a complex number
        result = complex1.negate();

        // Return the list of the 5th roots of this complex number
        List nth = complex1.nthRoot(5);

        // Computes the complex number raised to the power of another
        Complex pow = complex1.pow(complex2);

        // Computes the square root of a complex number
        Complex sqrt = complex1.sqrt();

        // Retrieve the real and imaginary parts of the result
        double real = complex1.getReal();
        double imag = complex1.getImaginary();

        // Obtain the tangent
        result = complex1.tan();

    }
}
