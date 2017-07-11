package com.BurritoMatic;

import java.util.Objects;

/**
 * Created by pratap on 4/29/17.
 */
public class IngredientCategory {

    private String ingredientCategory;

    public String getIngredientCategoryName() {
        return ingredientCategory;
    }

    public IngredientCategory(String category) {
        ingredientCategory = category;
    }

    @Override
    public String toString() {
        return this.ingredientCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientCategory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof IngredientCategory))
            return false;
        IngredientCategory category = (IngredientCategory) obj;
        return Objects.equals(ingredientCategory, category.ingredientCategory);
    }
}
