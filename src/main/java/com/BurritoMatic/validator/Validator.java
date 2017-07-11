package com.BurritoMatic.validator;

import com.BurritoMatic.Ingredient;
import com.BurritoMatic.Order.Order;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;

import java.util.List;

/**
 * Created by pratap on 4/29/17.
 */
public class Validator implements IValidator {
    public boolean validate(Order order) throws InvalidIngredientsSelectedException {
        return validateMeatIngredient(order);
    }

    private boolean validateMeatIngredient(Order order) throws InvalidIngredientsSelectedException {
        if (getMeatIngredientCount(order) == 1)
            return true;
        throw new InvalidIngredientsSelectedException("All burritos can have only 1 meat \n");
    }

    private int getMeatIngredientCount(Order order) {
        List<Ingredient> ingredients = order.getOrder();
        int meatIngredientCount = 0;
        for (Ingredient i : ingredients) {
            if (i.getIngredientCategory().getIngredientCategoryName().equals("Meat"))
                meatIngredientCount++;
        }
        return meatIngredientCount;
    }

    private int getSalsaIngredientCount(Order order) {
        List<Ingredient> ingredients = order.getOrder();
        int salsaIngredientCount = 0;
        for (Ingredient i : ingredients) {
            if (i.getIngredientCategory().getIngredientCategoryName().equals("Salsa"))
                salsaIngredientCount++;
        }
        return salsaIngredientCount;
    }

    private int getToppingIngredientCount(Order order) {
        List<Ingredient> ingredients = order.getOrder();
        int toppingIngredientCount = 0;
        for (Ingredient i : ingredients) {
            if (i.getIngredientCategory().getIngredientCategoryName().equals("Toppings"))
                toppingIngredientCount++;
        }
        return toppingIngredientCount;
    }

    boolean validateIngredientPresence(Order order, String ingredient) {
        List<Ingredient> ingredientList = order.getOrder();
        for (Ingredient i : ingredientList) {
            if (i.getIngredientName().equals(ingredient))
                return true;
        }
        return false;
    }

    boolean validateMeatCount(Order order, int count) throws InvalidIngredientsSelectedException {
        if (getMeatIngredientCount(order) <= count)
            return true;
        throw new InvalidIngredientsSelectedException(order.getOrderDish().getDishName() + " can have only " + count + "meat \n");
    }

    boolean validateSalsaCount(Order order, int count) throws InvalidIngredientsSelectedException {
        if (getSalsaIngredientCount(order) <= count)
            return true;
        throw new InvalidIngredientsSelectedException(order.getOrderDish().getDishName() + " can have only" + count + "salsa \n");
    }

    boolean validateToppingCount(Order order, int count) throws InvalidIngredientsSelectedException {
        if (getToppingIngredientCount(order) <= count)
            return true;
        throw new InvalidIngredientsSelectedException(order.getOrderDish().getDishName() + " can have only " + count + " topping \n");
    }
}
