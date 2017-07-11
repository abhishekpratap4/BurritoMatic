package com.BurritoMatic.validator;

import com.BurritoMatic.Order.Order;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;

/**
 * Created by pratap on 4/29/17.
 */
public class ThreeIngredientBurritoValidator extends Validator {
    @Override
    public boolean validate(Order order) throws InvalidIngredientsSelectedException {
        return super.validate(order) &&
                validateMeatCount(order, 1) &&
                validateSalsaCount(order, 1) &&
                validateToppingCount(order, 1);
    }
}
