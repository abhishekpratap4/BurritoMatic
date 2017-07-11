package com.BurritoMatic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pratap on 5/1/17.
 */
public class IngredientInventoryTest extends BaseTest {

    @Test
    public void testIsIngredientAvailableInInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        boolean actualResult = inventoryObj.isAvailableInInventory(new IngredientCategory("Base"), "Tortilla");
        //inventoryObj.printIngredientInventory();
        assertTrue(actualResult);
    }

    @Test
    public void testIsIngredientNotAvailableInInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        boolean actualResult = inventoryObj.isAvailableInInventory(new IngredientCategory("Base"), "Bread");
        //inventoryObj.printIngredientInventory();
        assertFalse(actualResult);
    }

    @Test
    public void testTakeIngredientFromInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        IngredientCategory category = new IngredientCategory("Salsa");
        Ingredient ingredient = new Ingredient(category, "Red Salsa");

        int itemsInInventoryBeforeOperation = inventoryObj.getInventory().get(category).get(ingredient);
        inventoryObj.takeIngredientFromInventory(ingredient);
        int itemsInInventoryAfterOperation = inventoryObj.getInventory().get(category).get(ingredient);

        //inventoryObj.printIngredientInventory();
        assertEquals(itemsInInventoryBeforeOperation - 1, itemsInInventoryAfterOperation);
    }

    @Test
    public void testAddNewCategoryAndIngredientToInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        //inventoryObj.printIngredientInventory();

        inventoryObj.addNewCategoryAndIngredientToInventory("Sauce", "Red Sauce", 5);
        //inventoryObj.printIngredientInventory();

        IngredientCategory category = new IngredientCategory("Sauce");
        Ingredient ingredient = new Ingredient(category, "Red Sauce");
        assertTrue(inventoryObj.getInventory().get(category).containsKey(ingredient));
    }

    @Test
    public void testAddNewIngredientToInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        //inventoryObj.printIngredientInventory();

        inventoryObj.addIngredientToInventory("Toppings", "Chilli", 5);
        //inventoryObj.printIngredientInventory();

        IngredientCategory category = new IngredientCategory("Toppings");
        Ingredient ingredient = new Ingredient(category, "Chilli");
        assertTrue(inventoryObj.getInventory().get(category).containsKey(ingredient));
    }

    @Test
    public void testUpdateIngredientQuantityInInventory() throws Exception {
        IngredientInventory inventoryObj = BaseTest.getIngredientInventory();
        //inventoryObj.printIngredientInventory();

        IngredientCategory category = new IngredientCategory("Base");
        Ingredient ingredient = new Ingredient(category, "Tortilla");
        int quantityBeforeUpdate = inventoryObj.getInventory().get(category).get(ingredient);

        inventoryObj.addIngredientToInventory("Base", "Tortilla", 2);
        //inventoryObj.printIngredientInventory();

        int quantityAfterUpdate = inventoryObj.getInventory().get(category).get(ingredient);
        assertEquals(quantityBeforeUpdate + 2, quantityAfterUpdate);
    }
}