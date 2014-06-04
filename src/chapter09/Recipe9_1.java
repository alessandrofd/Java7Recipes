package chapter09;

public class Recipe9_1 {
    public static void main(String[] args) {
        Recipe9_1 recipe = new Recipe9_1();
        recipe.start();
    }

    private void start() {
        try {
            int a = 5/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
