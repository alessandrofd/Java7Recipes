package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_4 {

    public static void main(String[] args) {
        changeCase();
    }

    public static void changeCase() {
        String s = "This String will change case.";
        System.out.println(s.toUpperCase());
        System.out.println(s.toLowerCase());
    }
}
