package chapter02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_8 {
    public static void main(String[] args) {
        findMatches();
        patternAndMatches();
    }

    /**
     * Solution #1
     */
    public static void findMatches() {
        String s = "Here is a long String...let's find a match!";

        // This will result in TRUE since it is an exact match
        boolean result = s.matches("Here is a long String...let's find a match!");
        System.out.println(result);

        // This will result in FALSE since the entire String does not match
        result = s.matches("Here is a long String...");
        System.out.println(result);

        s = "True";

        // This will test against both upper and lower case "T" ... this will be TRUE
        result = s.matches("[Tt]rue");
        System.out.println(result);

        // This will test for one or the other
        result = s.matches("[Tt]rue|[Ff]alse");
        System.out.println(result);

        // This will test to see if any numbers are present, in this case the person writing this String would be able
        // to like any Java release!
        s = "I love Java 7!";
        result = s.matches("I love Java [0-9]!");
        System.out.println(result);

        // This will test TRUE as well
        s = "I love Java 6!";
        result = s.matches("I love Java [0-9]!");
        System.out.println(result);

        // The following will test TRUE for any language that contains only one word for a name. This is because it
        // tests for any alphanumeric combination. Notice the space character between the numeric sequence...
        result = s.matches("I love .*[ 0-9]!");
        System.out.println(result);

        // The following String also matches
        s = "I love Jython 2.5.2!";
        result = s.matches("I love .*[ 0-9]!");
        System.out.println(result);

    }

    /**
     * Solution #2
     */
    public static void patternAndMatches() {
        String s = "I love Java 7!";
        boolean result = false;

        Pattern pattern = Pattern.compile("I love .*[ 0-9]!");
        Matcher matcher = pattern.matcher(s);
        result = matcher.matches();
        System.out.println(result);
    }
}
