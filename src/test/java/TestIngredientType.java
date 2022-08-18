import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import java.util.Arrays;


@RunWith(Enclosed.class)
public class TestIngredientType {
    public static final int SIZE = 2;

    @RunWith(Parameterized.class)
    public static class WithParams {
        @Parameterized.Parameters(name = "{index}: String {0} equals name of element {1}")
        public static Iterable<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"SAUCE", IngredientType.SAUCE},
                    {"FILLING", IngredientType.FILLING}
            });
        }

        private final String name;
        private final IngredientType ingredientType;

        public WithParams(String name, IngredientType ingredientType) {
            this.name = name;
            this.ingredientType = ingredientType;
        }

        @Test
        public void unitTestIngredientTypeHasElement() {
            Assert.assertEquals(name, ingredientType.name());
        }
    }


    public static class WithoutParams {
        @Test
        public void unitTestIngredientTypeHasValidSize() {
            Assert.assertEquals(SIZE, IngredientType.values().length);
        }
    }
}
