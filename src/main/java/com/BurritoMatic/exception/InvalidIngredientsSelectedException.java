package com.BurritoMatic.exception;

/**
 * Created by pratap on 4/30/17.
 */
public class InvalidIngredientsSelectedException extends Exception {

    public InvalidIngredientsSelectedException(String message) {
        System.out.print("Invalid ingredients selected \n" + message);
    }
}
