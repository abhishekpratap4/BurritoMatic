package com.BurritoMatic.validator;

import com.BurritoMatic.BaseTest;
import com.BurritoMatic.Constants;
import com.BurritoMatic.Dish;
import com.BurritoMatic.IngredientCategory;
import com.BurritoMatic.Order.Order;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pratap on 5/1/17.
 */
public class TwoIngredientBurritoValidatorTest extends BaseTest {
    Validator validator;
    Order order;
    Order failureOrder;

    @Before
    public void setUp() {
        Dish userDish = new Dish(Constants.TWO_ING_BURRITO, 1);
        order = new Order(userDish);
        order.appendToOrder(new IngredientCategory("Base"), "Tortilla");
        order.appendToOrder(new IngredientCategory("Meat"), "Chicken");
        order.appendToOrder(new IngredientCategory("Salsa"), "Red Salsa");
        order.appendToOrder(new IngredientCategory("Toppings"), "Sour Cream");


        failureOrder = new Order(userDish);
        failureOrder.appendToOrder(new IngredientCategory("Base"), "Tortilla");
        failureOrder.appendToOrder(new IngredientCategory("Meat"), "Chicken");
        failureOrder.appendToOrder(new IngredientCategory("Meat"), "Steak");
        failureOrder.appendToOrder(new IngredientCategory("Salsa"), "Red Salsa");
        failureOrder.appendToOrder(new IngredientCategory("Toppings"), "Gucamole");

        validator = new ValidatorFactory().getValidator(order.getOrderDish());
    }

    @Test
    public void testIsCorrectValidator() {
        assertTrue(validator instanceof TwoIngredientBurritoValidator);
    }

    @Test
    public void testValidateSuccess() throws Exception {
        assertTrue(validator.validate(order));
    }

    @Test(expected = InvalidIngredientsSelectedException.class)
    public void testValidateFailure() throws InvalidIngredientsSelectedException {
        validator.validate(failureOrder);
    }
}