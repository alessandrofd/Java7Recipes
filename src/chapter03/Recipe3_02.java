package chapter03;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Recipe3_02 {

    public static void main(String[] args) {
        formatDouble(new Double("345.9372"));
    }


    public static void formatDouble(Double myDouble) {
        NumberFormat numberFormatter = new DecimalFormat("##.000");
        String result = numberFormatter.format(myDouble);

        System.out.println(result);

        // Obtains an instance of NumberFormat class
        NumberFormat format = NumberFormat.getInstance();

        // Format a double value for the current locale
        result = format.format(83.404);
        System.out.println(result);

        // Format a double value for an Italian locale
        result = NumberFormat.getInstance(Locale.US).format(83.404);
        System.out.println(result);

        // Parse a String into a Number
        try {
            Number num = format.parse("75,736");
            System.out.println(num);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }
}
