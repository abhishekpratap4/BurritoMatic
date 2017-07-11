package com.BurritoMatic.validator;

import com.BurritoMatic.Constants;
import com.BurritoMatic.Dish;

/**
 * Created by pratap on 4/30/17.
 */
public class ValidatorFactory {

    public Validator getValidator(Dish dish) {

        Validator validator;

        switch (dish.getDishName()) {
            case Constants.THREE_ING_BURRITO:
                validator = new ThreeIngredientBurritoValidator();
                break;
            case Constants.TWO_ING_BIB_NO_TORTILLA:
                validator = new TwoIngredientBurritoInBowlValidator();
                break;
            case Constants.TWO_ING_BURRITO:
                validator = new TwoIngredientBurritoValidator();
                break;
            default:
                validator = null;
        }
        return validator;
    }

}
