import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestIngredient {
    public static final float DELTA = 0.0f;
    private static final String BAD_NAME =  "Incorrect ingredient name";
    private static final String BAD_TYPE =  "Incorrect ingredient type";
    private static final String BAD_PRICE = "Incorrect ingredient price";

    @Parameterized.Parameters(name = "{index}: IngredientType value:{0}, Price value:{1}, Name value:{2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE,   20,     ""},
                {IngredientType.FILLING, 20.2f,  "   "},
                {IngredientType.SAUCE,   -20,    "Vasya"},
                {IngredientType.FILLING, -20.2f, "Вася"},
                {IngredientType.SAUCE,   0,      "^(#^&*$*#^$"},
                {IngredientType.FILLING, 20.0,   null},
                {IngredientType.SAUCE,   20,     "222"},
        });
    }

    private final IngredientType ingredientType;
    private final float price;
    private final String name;
    private Ingredient ingredient;

    public TestIngredient(IngredientType ingredientType, float price, String name) {
        this.ingredientType = ingredientType;
        this.price = price;
        this.name = name;
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, name, price);
    }

    @Test
    public void unitTestGetType(){
        Assert.assertEquals(BAD_TYPE, ingredientType, ingredient.getType());
    }

    @Test
    public void unitTestGetPrice(){
        Assert.assertEquals(BAD_PRICE, price, ingredient.getPrice(), DELTA);
    }

    @Test
    public void unitTestGetName(){
        Assert.assertEquals(BAD_NAME, name, ingredient.getName());
    }
}
