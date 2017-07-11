package com.BurritoMatic.exception;

/**
 * Created by pratap on 4/30/17.
 */
public class InSufficientIngredientsInInventoryException extends Exception {

    public InSufficientIngredientsInInventoryException(String message){
        System.out.print("Insufficient Ingredients In IngredientInventory \n" + message);
    }

}
