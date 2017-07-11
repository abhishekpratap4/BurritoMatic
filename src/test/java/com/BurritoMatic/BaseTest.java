package com.BurritoMatic;

import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 * Created by pratap on 5/1/17.
 */

@Ignore
public class BaseTest {

    private static IngredientInventory ingredientInventory;
    private static CashInventory cashInventory;
    private static Menu menu;

    public static IngredientInventory getIngredientInventory() {
        return ingredientInventory;
    }

    public static Menu getMenu() {
        return menu;
    }

    public static CashInventory getCashInventory() {
        return cashInventory;
    }

    @BeforeClass
    public static void setUpBaseClass() {
        ingredientInventory = IngredientInventory.getIngredientInventoryInstance();
        cashInventory = CashInventory.getCashInventoryInstance();
        menu = Menu.getMenuInstance();
        menu.printMenu();
    }
}
