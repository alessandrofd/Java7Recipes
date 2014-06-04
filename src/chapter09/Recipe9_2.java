package chapter09;

public class Recipe9_2 {
    public static void main(String[] args) {
        Recipe9_2 recipe = new Recipe9_2();
        recipe.start();
    }

    private void start() {
        assert (true) : "It didn't work";

        assert (false) : "false";
    }
}
