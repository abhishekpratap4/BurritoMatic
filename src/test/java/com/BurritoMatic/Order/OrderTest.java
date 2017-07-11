package com.BurritoMatic.Order;

import com.BurritoMatic.Constants;
import com.BurritoMatic.Dish;
import com.BurritoMatic.Ingredient;
import com.BurritoMatic.IngredientCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by pratap on 5/1/17.
 */
public class OrderTest {

    Order order;

    @Before
    public void setUp() {
        Dish userDish = new Dish(Constants.TWO_ING_BIB_NO_TORTILLA, 1);
        order = new Order(userDish);
        order.appendToOrder(new IngredientCategory("Base"), "Rice");
    }

    @Test
    public void testAppendToOrderSuccess() throws Exception {
        assertTrue(order.getOrder().contains(new Ingredient(new IngredientCategory("Base"), "Rice")));
    }

    @Test
    public void testAppendToOrderFailure() throws Exception {
        assertFalse(order.getOrder().contains(new Ingredient(new IngredientCategory("Base"), "Tortilla")));
    }
}