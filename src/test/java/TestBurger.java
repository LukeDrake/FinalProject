import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

import static org.mockito.Mockito.*;

public class TestBurger {

    private static final String BAD_SET_BUN_MESSAGE = "Wrong bun selected";
    private static final String BAD_ADD_INGREDIENT_MESSAGE = "Ingredient not added";
    private static final String BAD_REMOVE_INGREDIENT_MESSAGE = "Ingredient not deleted";
    private static final String BAD_MOVE_INGREDIENT_MESSAGE = "Ingredient not deleted";
    private static final String BAD_GET_BURGER_PRICE_MESSAGE = "Wrong burger price";
    private static final String BAD_GET_BURGER_RECEIPT_MESSAGE = "Wrong bill";


    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(BAD_SET_BUN_MESSAGE, burger.bun, bun);
    }

    @Test
    public void testAddAndRemoveIngredient() {
        burger.addIngredient(ingredient);
        Assert.assertFalse(BAD_ADD_INGREDIENT_MESSAGE, burger.ingredients.isEmpty());

        burger.removeIngredient(0);
        Assert.assertTrue(BAD_REMOVE_INGREDIENT_MESSAGE, burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        String str = "Peperoni";
        Ingredient mock = mock(Ingredient.class, withSettings().name(str));
        burger.addIngredient(mock);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(BAD_MOVE_INGREDIENT_MESSAGE, burger.ingredients.get(1).toString(), str);
    }

    @Test
    public void testGetBurgerPrice() {
        float expectedPrice = 40f;

        when(bun.getPrice()).thenReturn(10f);
        when(ingredient.getPrice()).thenReturn(20f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();
        Assert.assertEquals(BAD_GET_BURGER_PRICE_MESSAGE, expectedPrice, actualPrice, 0);
    }

    @Test
    public void testGetBurgerReceipt() {
        final String bunName = "Garlic";
        final String ingredientName = "Mustard";
        // Random ingredient
        IngredientType ingredientType = IngredientType.values()[new Random().nextInt(IngredientType.values().length)];

        when(bun.getName()).thenReturn(bunName);
        when(ingredient.getName()).thenReturn(ingredientName);
        when(ingredient.getType()).thenReturn(ingredientType);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String actualReceipt = burger.getReceipt();
        String expectedReceipt = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bunName, ingredientType.toString().toLowerCase(), ingredientName, bunName, ingredient.getPrice());
        Assert.assertEquals(BAD_GET_BURGER_RECEIPT_MESSAGE, expectedReceipt, actualReceipt);
    }
}
