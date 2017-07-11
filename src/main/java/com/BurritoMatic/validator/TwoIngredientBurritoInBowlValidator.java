package com.BurritoMatic.validator;

import com.BurritoMatic.Order.Order;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;

/**
 * Created by pratap on 4/29/17.
 */
public class TwoIngredientBurritoInBowlValidator extends Validator {
    @Override
    public boolean validate(Order order) throws InvalidIngredientsSelectedException {
        return super.validate(order) &&
                validateTortillaAbsence(order) &&
                validateMeatCount(order, 1) &&
                validateSalsaCount(order, 1);
    }

    private boolean validateTortillaAbsence(Order order) throws InvalidIngredientsSelectedException {
        if (super.validateIngredientPresence(order, "Tortilla")) {
            throw new InvalidIngredientsSelectedException(order.getOrderDish().getDishName() + " cannot have a Tortilla \n");
        }
        return true;
    }
}
