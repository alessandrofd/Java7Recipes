package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_3 {
    public static void main(String[] args) {
        trimString();
    }

    public static void trimString() {
        String myString = "      This is a string that contains whitespaces.    ";
        System.out.println('['+ myString + ']');
        System.out.println('[' + myString.trim() + ']');
    }
}
