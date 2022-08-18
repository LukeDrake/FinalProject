import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestBun {
    public static final float DELTA = 0.0f;
    private static final String BAD_NAME =  "Incorrect bun name";
    private static final String BAD_PRICE = "Incorrect bun price";

    private final float price;
    private final String name;
    private Bun bun;

    @Parameterized.Parameters(name = "{index}: IngredientType value:{0}, Price value:{1}, Name value:{2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {20,     ""},
                {20.2f,  "   "},
                {-20,    "Vasya"},
                {-20.2f, "Вася"},
                {0,      "^(#^&*$*#^$"},
                {20.0,   null},
                {20,     "222"},
        });
    }

    public TestBun(float price, String name) {
        this.price = price;
        this.name = name;
    }
    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void unitTestGetName(){
        Assert.assertEquals(BAD_NAME, name, bun.getName());
    }

    @Test
    public void unitTestGetPrice(){
        Assert.assertEquals(BAD_PRICE, price, bun.getPrice(), DELTA);
    }
}
