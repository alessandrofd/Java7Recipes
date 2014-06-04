package chapter02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_9 {
    public static void main(String[] args) {
        findAndReplaceWithPatterns();
    }

    public static void findAndReplaceWithPatterns() {
        String s = "I love Java 7! It is my favorite language. " +
                "Java 7 is the 7th version of this great programming language.";

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(s);

        System.out.println("Original :" + s);
        System.out.println("Replacement : " + matcher.replaceAll("6"));
    }
}
