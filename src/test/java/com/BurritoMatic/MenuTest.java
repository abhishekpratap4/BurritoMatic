package com.BurritoMatic;

import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by pratap on 5/1/17.
 */
public class MenuTest extends BaseTest {

    @Test
    public void testThatSelectedDishIsPresentInMenu() throws Exception {
        int userChoice = 1;
        Menu menuObject = BaseTest.getMenu();
        Optional<Dish> actualResult = menuObject.isUserBurritoChoiceValid(userChoice);
        Optional<Dish> expectedResult = Optional.ofNullable(new Dish(Constants.TWO_ING_BIB_NO_TORTILLA, 1));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testThatSelectedDishIsAbsentInMenu() throws Exception {
        int userChoice = 10;
        Menu menuObject = BaseTest.getMenu();
        Optional<Dish> actualResult = menuObject.isUserBurritoChoiceValid(userChoice);
        assertEquals(Optional.ofNullable(null), actualResult);
    }

    @Test
    public void testAddNewBurritoToMenu() throws Exception {
        Menu menuObject = BaseTest.getMenu();
        int id = menuObject.addOrUpdateBurritoInMenu("Abhishek's Burrito", 10.99f);
        //menuObject.printMenu();

        Dish dish = new Dish("Abhishek's Burrito", id);
        assertTrue(menuObject.getMenu().containsKey(dish) && menuObject.getMenu().get(dish) == 10.99f);
    }

    @Test
    public void testUpdateBurritoPriceInMenu() throws Exception {
        Menu menuObject = BaseTest.getMenu();
        int id = menuObject.addOrUpdateBurritoInMenu("2-Ingredient Burrito", 12.99f);
        //menuObject.printMenu();

        Dish dish = new Dish("2-Ingredient Burrito", id);
        assertTrue(menuObject.getMenu().containsKey(dish) && menuObject.getMenu().get(dish) == 12.99f);
    }

    @Test
    public void testAddIngredientAdditionalFee() throws Exception {
        Menu menuObject = BaseTest.getMenu();
        menuObject.addOrUpdateIngredientAdditionalFee("Base", "Indian Bread", 5.00f);
        //menuObject.printMenu();
        Ingredient ingredient = new Ingredient(new IngredientCategory("Base"), "Indian Bread");
        assertTrue(menuObject.getExtraChargesMenu().containsKey(ingredient) && menuObject.getExtraChargesMenu().get(ingredient) == 5.00f);
    }

    @Test
    public void testUpdateIngredientAdditionalFee() throws Exception {
        Menu menuObject = BaseTest.getMenu();
        menuObject.addOrUpdateIngredientAdditionalFee("Salsa", "Queso", 5.00f);
        //menuObject.printMenu();
        Ingredient ingredient = new Ingredient(new IngredientCategory("Salsa"), "Queso");
        assertTrue(menuObject.getExtraChargesMenu().containsKey(ingredient) && menuObject.getExtraChargesMenu().get(ingredient) == 5.00f);
    }
}