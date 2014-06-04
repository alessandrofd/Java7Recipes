package chapter06;

public class Recipe6_3 {
    public static void main(String[] args) {
        Recipe6_3 recipe = new Recipe6_3();
        recipe.start();
    }

    private void start() {
        try {
            callSomeFunctionThatMightThrow(null);
        } catch (NullPointerException e) {
            System.out.println("There was a null parameter!");
        }
    }

    private void callSomeFunctionThatMightThrow(Object o) {
        if (o == null) throw new NullPointerException("The object is null");
    }
}
