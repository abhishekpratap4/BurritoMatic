package com.BurritoMatic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by pratap on 4/29/17.
 */
// Implemented as a Singelton as I have assumed that there will be only 1 Menu instance
public class Menu {

    private static Map<Dish, Float> menu;
    private static Map<Ingredient, Float> extraChargesMenu;

    public static Map<Ingredient, Float> getExtraChargesMenu() {
        return extraChargesMenu;
    }

    public static Map<Dish, Float> getMenu() {
        return menu;
    }

    private static class MenuSingeltonHelper {
        private static final Menu menu = new Menu();
    }

    public static Menu getMenuInstance() {
        return MenuSingeltonHelper.menu;
    }

    @Override
    public String toString() {
        StringBuilder menuStringBuilder = new StringBuilder();
        for (Map.Entry<Dish, Float> entry : menu.entrySet()) {
            menuStringBuilder.append(entry.getKey()).append(" : $").append(entry.getValue()).append("\n");
        }

        for (Map.Entry<Ingredient, Float> entry : extraChargesMenu.entrySet()) {
            menuStringBuilder.append(entry.getKey().getIngredientName()).append(" : $").append(entry.getValue()).append("\n");
        }
        return menuStringBuilder.toString();
    }

    private Menu() {
        menu = new HashMap<>();
        menu.put(new Dish(Constants.TWO_ING_BIB_NO_TORTILLA, 1), 3.99f);
        menu.put(new Dish(Constants.TWO_ING_BURRITO, 2), 4.99f);
        menu.put(new Dish(Constants.THREE_ING_BURRITO, 3), 5.99f);

        extraChargesMenu = new HashMap<>();
        extraChargesMenu.put(new Ingredient(new IngredientCategory("Toppings"), "Gucamole"), 1.25f);
        extraChargesMenu.put(new Ingredient(new IngredientCategory("Salsa"), "Queso"), 1.50f);
    }

    public void printMenu() {
        System.out.println(this.toString());
    }

    public Optional<Dish> isUserBurritoChoiceValid(int userChoice) {
        Optional<Dish> dishOptional;
        for (Map.Entry<Dish, Float> entry : menu.entrySet()) {
            if (userChoice == entry.getKey().getDishId()) {
                dishOptional = Optional.ofNullable(entry.getKey());
                return dishOptional;
            }
        }
        return Optional.ofNullable(null);
    }

    public int addOrUpdateBurritoInMenu(String ingredientName, float price) {
        for (Map.Entry<Dish, Float> entry : menu.entrySet()) {
            if (ingredientName.equals(entry.getKey().getDishName())) {
                menu.put(entry.getKey(), price);
                return entry.getKey().getDishId();
            }
        }
        int newBurritoId = menu.size() + 1;
        menu.put(new Dish(ingredientName, newBurritoId), price);
        return newBurritoId;
    }

    public void addOrUpdateIngredientAdditionalFee(String category, String ingredientName, float price) {
        Ingredient ingredient = new Ingredient(new IngredientCategory(category), ingredientName);
        extraChargesMenu.put(ingredient, price);
    }
}
