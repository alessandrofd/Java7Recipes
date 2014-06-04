package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_1 {

    public static void main(String[] args) {
        substringExample();
    }

    static public void substringExample() {
        String originalString = "This is the original string.";

        System.out.println(originalString.substring(0, originalString.length()));
        System.out.println(originalString.substring(5, 20));
        System.out.println(originalString.substring(12));
    }
}
