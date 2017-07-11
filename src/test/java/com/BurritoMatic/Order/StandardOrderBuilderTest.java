package com.BurritoMatic.Order;

import com.BurritoMatic.*;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Created by pratap on 5/1/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StandardOrderBuilderTest {

    AbstractOrderBuilder builder;
    Order order;
    Order additionalFeeOrder;
    Order outOfStockOrder;
    IngredientInventory inventory;
    CashInventory cashInventory;
    Menu menu;

    @Before
    public void setUp() {

        inventory = IngredientInventory.getIngredientInventoryInstance();
        System.out.println("StandardOrderTestBuilder inventory");
        inventory.printIngredientInventory();
        cashInventory = CashInventory.getCashInventoryInstance();
        menu = Menu.getMenuInstance();
        menu.printMenu();

        OrderBuilderFactory orderBuilderFactory = new OrderBuilderFactory();
        builder = orderBuilderFactory.getOrderBuilder(Constants.STANDARD);
        Dish userDish = new Dish(Constants.TWO_ING_BIB_NO_TORTILLA, 1);

        order = new Order(userDish);
        order.appendToOrder(new IngredientCategory("Base"), "Rice");
        order.appendToOrder(new IngredientCategory("Meat"), "Chicken");
        order.appendToOrder(new IngredientCategory("Salsa"), "Red Salsa");
        order.appendToOrder(new IngredientCategory("Toppings"), "Sour Cream");

        additionalFeeOrder = new Order(userDish);
        additionalFeeOrder.appendToOrder(new IngredientCategory("Base"), "Rice");
        additionalFeeOrder.appendToOrder(new IngredientCategory("Meat"), "Chicken");
        additionalFeeOrder.appendToOrder(new IngredientCategory("Salsa"), "Red Salsa");
        additionalFeeOrder.appendToOrder(new IngredientCategory("Toppings"), "Gucamole");

        outOfStockOrder = new Order(userDish);
        outOfStockOrder.appendToOrder(new IngredientCategory("Base"), "Rice");
        outOfStockOrder.appendToOrder(new IngredientCategory("Meat"), "Chicken");
        outOfStockOrder.appendToOrder(new IngredientCategory("Salsa"), "Green Salsa");
        outOfStockOrder.appendToOrder(new IngredientCategory("Toppings"), "Sour Cream");
    }

    @Test
    public void testIsStandardOrderBuilder() throws Exception {
        assertTrue(builder instanceof StandardOrderBuilder);
    }

    @Test
    public void testBuildOrderSuccess() throws Exception {
        assertTrue(builder.buildOrder(order, inventory) == 3.99f);
    }

    @Test
    public void testBuildOrderFailure() throws Exception {
        assertFalse(builder.buildOrder(order, inventory) == 0.0f);
    }

    @Test
    public void testBuildOrderWithAdditionalFeeIngredient() throws Exception {
        assertTrue(builder.buildOrder(additionalFeeOrder, inventory) == 5.24f);
    }

    @Test
    public void testIngredientNotEntitledForExtraCharge() throws Exception {
        Ingredient ingredient = new Ingredient(new IngredientCategory("Base"), "Rice");
        assertTrue(builder.isIngredientEntitledForExtraCharge(ingredient, menu.getExtraChargesMenu()) == 0.0f);
    }

    @Test
    public void testIngredientEntitledForExtraCharge() throws Exception {
        Ingredient ingredient = new Ingredient(new IngredientCategory("Toppings"), "Gucamole");
        assertTrue(builder.isIngredientEntitledForExtraCharge(ingredient, menu.getExtraChargesMenu()) == 1.25f);
    }

    @Test(expected = InSufficientIngredientsInInventoryException.class)
    public void testOutOfStock() throws Exception {
        // This loop will break when ingredients go out of stock
        while (true) {
            builder.buildOrder(outOfStockOrder, inventory);
            BaseTest.getIngredientInventory().printIngredientInventory();
        }
    }
}