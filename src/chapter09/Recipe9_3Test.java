package chapter09;

import org.junit.Assert;
import org.junit.Test;

public class Recipe9_3Test {
    @Test
    public void testAddNumbers() throws Exception {
        Recipe9_3 recipe = new Recipe9_3();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Assert.assertEquals(i + j, recipe.addNumbers(i, j));
            }
        }

    }

    @Test
    public void testSubtractNumbers() throws Exception {
        Recipe9_3 recipe = new Recipe9_3();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Assert.assertEquals(i-j, recipe.subtractNumbers(i, j));
            }
        }

    }
}
