package com.BurritoMatic.Order;

import com.BurritoMatic.IngredientInventory;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;

/**
 * Created by pratap on 4/30/17.
 */
public class AlaCarteOrderBuilder extends AbstractOrderBuilder {
    @Override
    public float buildOrder(Order order, IngredientInventory ingredientInventory) throws InSufficientIngredientsInInventoryException {
        return 0;
    }
}
