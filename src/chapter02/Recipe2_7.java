package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_7 {
    public static void main(String[] args) {
        breakToChars();
    }

    public static void breakToChars() {
        String s = "Break down to chars";

        System.out.println(s);

        for (char c : s.toCharArray())
            System.out.println(c);

        for (int i = 0; i < s.length(); i++)
            System.out.println(s.charAt(i));

    }
}
