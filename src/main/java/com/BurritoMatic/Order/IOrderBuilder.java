package com.BurritoMatic.Order;

import com.BurritoMatic.IngredientInventory;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;

/**
 * Created by pratap on 4/30/17.
 */
interface IOrderBuilder {

    float buildOrder(Order order, IngredientInventory ingredientInventory) throws InSufficientIngredientsInInventoryException;

}
