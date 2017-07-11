package com.BurritoMatic.validator;

import com.BurritoMatic.Order.Order;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;

/**
 * Created by pratap on 4/29/17.
 */
interface IValidator {
    boolean validate(Order order) throws InvalidIngredientsSelectedException;
}
