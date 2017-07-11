package com.BurritoMatic;

import java.util.*;

/**
 * Created by pratap on 4/29/17.
 */
public class Ingredient {

    private IngredientCategory ingredientCategory;
    private String ingredientName;

    public IngredientCategory getIngredientCategory() {
        return ingredientCategory;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public Ingredient(IngredientCategory category, String name) {
        ingredientCategory = category;
        ingredientName = name;
    }

    @Override
    public String toString() {
        return ingredientName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientCategory, ingredientName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Ingredient))
            return false;
        Ingredient ingredient = (Ingredient) obj;
        return Objects.equals(ingredientCategory, ingredient.ingredientCategory) && Objects.equals(ingredientName, ingredient.ingredientName);
    }
}
