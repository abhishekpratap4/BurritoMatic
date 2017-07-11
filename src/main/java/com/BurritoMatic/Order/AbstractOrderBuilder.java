package com.BurritoMatic.Order;

import com.BurritoMatic.Ingredient;
import com.BurritoMatic.IngredientInventory;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;

import java.util.Map;

/**
 * Created by pratap on 4/30/17.
 */
public abstract class AbstractOrderBuilder implements IOrderBuilder {
    @Override
    public abstract float buildOrder(Order order, IngredientInventory ingredientInventory) throws InSufficientIngredientsInInventoryException;

    public float isIngredientEntitledForExtraCharge(Ingredient ingredient, Map<Ingredient, Float> extraChargesMenu) {
        float extraCharge = 0.0f;
        if (extraChargesMenu.containsKey(ingredient)) {
            extraCharge = extraChargesMenu.get(ingredient);
        }
        return extraCharge;
    }
}
