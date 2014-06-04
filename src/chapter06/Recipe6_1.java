package chapter06;

public class Recipe6_1 {
    public static void main(String[] args) {
        Recipe6_1 recipe = new Recipe6_1();
        recipe.start();
    }

    private void start() {
        System.out.println("Is the string 1234 longer than 5 chars?: " + isStringLongerThanFiveCharacter("1234"));
        System.out.println("Is the string 12345 longer than 5 chars?: " + isStringLongerThanFiveCharacter("12345"));
        System.out.println("Is the string 123456 longer than 5 chars?: " + isStringLongerThanFiveCharacter("123456"));
        System.out.println("Is the string null longer than 5 chars?: " + isStringLongerThanFiveCharacter(null));
    }

    private boolean isStringLongerThanFiveCharacter(String aString) {
        try {
            return aString.length() > 5;
        } catch (NullPointerException e) {
            System.out.println("An Exceptions Happened!");
            return false;
        }
    }
}
