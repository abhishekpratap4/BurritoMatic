package com.BurritoMatic.Order;

import com.BurritoMatic.Dish;
import com.BurritoMatic.Ingredient;
import com.BurritoMatic.IngredientInventory;
import com.BurritoMatic.Menu;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;

import java.util.Map;

/**
 * Created by pratap on 4/30/17.
 */
public class StandardOrderBuilder extends AbstractOrderBuilder {

    @Override
    public float buildOrder(Order order, IngredientInventory ingredientInventory) throws InSufficientIngredientsInInventoryException {
        float billAmount = 0.0f;
        Map<Dish, Float> menu = Menu.getMenu();
        Map<Ingredient, Float> extraChargesMenu = Menu.getExtraChargesMenu();

        for (Ingredient i : order.getOrder()) {
            //find ingredient in ingredientInventory and reduce its count
            ingredientInventory.takeIngredientFromInventory(i);

            // check if some ingredient is entitled for extra charge
            billAmount += super.isIngredientEntitledForExtraCharge(i, extraChargesMenu);
        }

        for (Map.Entry<Dish, Float> entry : menu.entrySet()) {
            if (entry.getKey().getDishName().equals(order.getOrderDish().getDishName())) {
                billAmount += entry.getValue();
                break;
            }
        }
        return billAmount;
    }
}
