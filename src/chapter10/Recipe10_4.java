package chapter10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex.java in the original source code.
 */
public class Recipe10_4 {
    private String jaText = "Fight 文字化け!";
    private String enText = "The fat cat sat on the mat with the brown rat.";
    private String enRegEx = "^The \\w+ cat.*";
    private String jaRegEx = ".*文字.*";
    private String jaRegExEscaped = ".*\u6587\u5B57.*";

    public static void main(String[] args) {
        Recipe10_4 recipe = new Recipe10_4();
        recipe.run();
    }

    private void run() {
        demoStringMatch();
        demoStringReplace();
        demoStringSplit();
        demoSimple();
        demoComplex();
    }

    public void demoStringMatch() {
        boolean found = false;
        found = enText.matches(enRegEx);
        if (found) {
            System.out.printf("Matches %s%n", enRegEx);
        }

        found = jaText.matches(jaRegEx);
        if (found) {
            System.out.printf("Matches %s%n", jaRegEx);
        }

        found = jaText.matches(jaRegExEscaped);
        if (found) {
            System.out.printf("Matches %s%n", jaRegExEscaped);
        }
    }

    public void demoStringReplace() {
        String replaced = jaText.replaceFirst("\u6587\u5B57化け", "mojibake");
        System.out.printf("Replaced %s%n", replaced);
    }

    public void demoStringSplit() {
        String[] fields = enText.split("\\s", 3);
        for (String field : fields)
            System.out.printf("Split: %s%n", field);
    }

    public void demoSimple() {
        Pattern p = Pattern.compile(".at");
        Matcher m = p.matcher(enText);
        while (m.find())
            System.out.printf("%s%n", m.group());
    }

    public void demoComplex() {
        Pattern p = Pattern.compile("文字");
        Matcher m = p.matcher(jaText);
        while (m.find())
            System.out.println(m.group());
    }

}
