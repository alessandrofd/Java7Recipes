package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_6 {
    public static void main(String[] args) {
        stringsToNumbers();
        stringsToNumbersParseInt();
    }

    public static void stringsToNumbers() {
        String one = "1";
        String two = "2";
        int result = Integer.valueOf(one) + Integer.valueOf(two);
        System.out.println(result);
    }

    public static void stringsToNumbersParseInt() {
        String one = "1";
        String two = "2";
        int result = Integer.parseInt(one) + Integer.parseInt(two);
        System.out.println(result);
    }
}
