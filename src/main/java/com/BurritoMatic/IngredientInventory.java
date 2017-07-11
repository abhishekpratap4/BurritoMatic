package com.BurritoMatic;

import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;

import java.util.*;

/**
 * Created by pratap on 4/29/17.
 */

// Implemented as a Singelton as I have assumed that there will be only 1 ingredient inventory instance
public class IngredientInventory {

    private Map<IngredientCategory, Map<Ingredient, Integer>> inventory;
    private Set<IngredientCategory> ingredientCategories;

    public Set<IngredientCategory> getIngredientCategories() {
        return ingredientCategories;
    }

    public Map<IngredientCategory, Map<Ingredient, Integer>> getInventory() {
        return inventory;
    }

    private IngredientInventory() {
        inventory = new LinkedHashMap<>();
        ingredientCategories = new LinkedHashSet<>();
        initializeInventory(5);
    }

    private static class IngredientInventorySingeltonHelper {
        public static final IngredientInventory ingredientInventory = new IngredientInventory();
    }

    public static IngredientInventory getIngredientInventoryInstance() {
        return IngredientInventorySingeltonHelper.ingredientInventory;
    }

    private void initializeInventory(int defaultCount) {
        IngredientCategory base = new IngredientCategory("Base");
        IngredientCategory meat = new IngredientCategory("Meat");
        IngredientCategory salsa = new IngredientCategory("Salsa");
        IngredientCategory toppings = new IngredientCategory("Toppings");

        ingredientCategories.add(base);
        ingredientCategories.add(meat);
        ingredientCategories.add(salsa);
        ingredientCategories.add(toppings);

        Map<Ingredient, Integer> baseTypes = new TreeMap<>();
        baseTypes.put(new Ingredient(base, "Tortilla"), defaultCount);
        baseTypes.put(new Ingredient(base, "Rice"), defaultCount);

        Map<Ingredient, Integer> meatTypes = new LinkedHashMap<>();
        meatTypes.put(new Ingredient(meat, "Chicken"), defaultCount);
        meatTypes.put(new Ingredient(meat, "Steak"), defaultCount);

        Map<Ingredient, Integer> salsaTypes = new LinkedHashMap<>();
        salsaTypes.put(new Ingredient(salsa, "Red Salsa"), defaultCount);
        salsaTypes.put(new Ingredient(salsa, "Green Salsa"), defaultCount);
        salsaTypes.put(new Ingredient(salsa, "Queso"), defaultCount);

        Map<Ingredient, Integer> toppingTypes = new LinkedHashMap<>();
        toppingTypes.put(new Ingredient(toppings, "Grated Cheese"), defaultCount);
        toppingTypes.put(new Ingredient(toppings, "Sour Cream"), defaultCount);
        toppingTypes.put(new Ingredient(toppings, "Gucamole"), defaultCount);

        inventory.put(base, baseTypes);
        inventory.put(meat, meatTypes);
        inventory.put(salsa, salsaTypes);
        inventory.put(toppings, toppingTypes);
    }

    public void printIngredientInventory() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder inventoryStringBuilder = new StringBuilder("\nIngredientInventory Status\n");
        for (Map.Entry<IngredientCategory, Map<Ingredient, Integer>> entry : inventory.entrySet()) {
            inventoryStringBuilder.append("**").append(entry.getKey().toString()).append("**\n");
            for (Map.Entry<Ingredient, Integer> ingredient : entry.getValue().entrySet()) {
                inventoryStringBuilder.append(ingredient.getKey()).append(" (In Stock Qty - ").append(ingredient.getValue()).append(")").append("\n");
            }
        }
        return inventoryStringBuilder.toString();
    }

    // Marked as synchronized to avoid multiple thread interference while checking inventory
    public synchronized boolean isAvailableInInventory(IngredientCategory category, String ingChoice) {
        if (inventory.containsKey(category)) {
            Map<Ingredient, Integer> ingredientMap = inventory.get(category);
            Ingredient temp = new Ingredient(category, ingChoice);
            if (ingredientMap.containsKey(temp) && ingredientMap.get(temp) > 0) {
                return true;
            }
        }
        return false;
    }

    // Marked as synchronized to avoid multiple thread interference while taking out items from inventory
    public synchronized void takeIngredientFromInventory(Ingredient ingredient) throws InSufficientIngredientsInInventoryException {
        Map<Ingredient, Integer> categoryIngredients = inventory.get(new IngredientCategory(ingredient.getIngredientCategory().getIngredientCategoryName()));
        if (categoryIngredients.containsKey(ingredient) && categoryIngredients.get(ingredient) > 0) {
            categoryIngredients.put(ingredient, categoryIngredients.get(ingredient) - 1);
        } else {
            throw new InSufficientIngredientsInInventoryException(ingredient.getIngredientName() + " not present in inventory!");
        }
    }

    public synchronized boolean addNewCategoryAndIngredientToInventory(String category, String ing, int quantity) {
        IngredientCategory ingredientCategory = new IngredientCategory(category);

        if (!inventory.containsKey(ingredientCategory)) {
            ingredientCategories.add(ingredientCategory);

            Map<Ingredient, Integer> categoryMap = new LinkedHashMap<>();
            Ingredient ingredient = new Ingredient(ingredientCategory, ing);
            categoryMap.put(ingredient, quantity);
            inventory.put(ingredientCategory, categoryMap);

            return true;
        }
        System.out.println("\nIngredient Category already exists. Please add ingredient to existing category!");
        return false;
    }

    public synchronized boolean addIngredientToInventory(String category, String ing, int quantity) {
        IngredientCategory ingredientCategory = new IngredientCategory(category);
        if (inventory.containsKey(ingredientCategory)) {
            Map<Ingredient, Integer> categoryMap = inventory.get(ingredientCategory);
            Ingredient ingredient = new Ingredient(ingredientCategory, ing);
            if (categoryMap.containsKey(ingredient)) {
                categoryMap.put(ingredient, categoryMap.get(ingredient) + quantity);
            } else {
                categoryMap.put(ingredient, quantity);
            }
            return true;
        }
        System.out.println("\nIngredient Category doesn't exist in inventory. Please add category and then add ingredient to new category!");
        return false;
    }
}
