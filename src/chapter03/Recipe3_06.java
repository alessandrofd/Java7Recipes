package chapter03;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;

import java.text.NumberFormat;

/**
 * Created by Alessandro on 08/04/2014.
 */
public class Recipe3_06 {

    public static void main(String[] args) {
        formatComplex();
        Complex returnedComplex = parseComplex("2.837 + 83.9i");
    }

    public static void formatComplex() {
        ComplexFormat format = new ComplexFormat(); // default format
        Complex c = new Complex(3.1415, 7.849996);
        String s = format.format(c);
        System.out.println(s);

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(3);
        numberFormat.setMaximumFractionDigits(3);

        ComplexFormat complexFormat2 = new ComplexFormat(numberFormat);
        s = complexFormat2.format(c);
        System.out.println(s);
    }

    public static Complex parseComplex(String s) {
        ComplexFormat complexFormat = new ComplexFormat();
        Complex complex = null;
        complex = complexFormat.parse(s);
        return complex;
    }
}
