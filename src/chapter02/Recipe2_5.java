package chapter02;

/**
 * Created by Alessandro on 01/04/2014.
 */
public class Recipe2_5 {
    public static void main(String[] args) {
        concatExample();
        concatOperatorExample();
        stringBufferExample();
    }

    public static void concatExample() {
        String one = "Hello";
        String two = "Java7";
        String result = one.concat(" ".concat(two.substring(0, two.length())));

        System.out.println(result);
    }

    public static void  concatOperatorExample() {
        String one = "Hello";
        String two = "Java7";
        String result = one + " " + two;

        System.out.println(result);
    }

    public static void  stringBufferExample() {
        String one = "Hello";
        String two = "Java7";
        StringBuffer buffer = new StringBuffer();
        buffer.append(one).append(' ').append(two);
        String result = buffer.toString();
        System.out.println(result);
    }

}
