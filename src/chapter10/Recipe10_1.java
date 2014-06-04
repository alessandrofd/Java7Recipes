package chapter10;

/**
 * DigitConversion.java in the original source code
 */
public class Recipe10_1 {
    public static void main(String[] args) {
        Recipe10_1 recipe = new Recipe10_1();
        recipe.run();
    }

    private void run() {
        int x = 0;
        for (int c = 0; c < 0x10FFFF; c++) {
            if (Character.isDigit(c)) {
                ++x;
                System.out.printf("Codepoint 0x%04X\tCharacter: %c\tDigit: %d\tName: %s%n", c, c,
                        Character.digit(c, 10), Character.getName(c));
            }
        }
        System.out.printf("Total digits: %d", x);
    }
}
