package com.BurritoMatic.Order;

import com.BurritoMatic.Constants;

/**
 * Created by pratap on 4/30/17.
 */
public class OrderBuilderFactory {

    public AbstractOrderBuilder getOrderBuilder(String builderType) {
        AbstractOrderBuilder builder;

        switch (builderType) {
            case Constants.STANDARD:
                builder = new StandardOrderBuilder();
                break;
            case Constants.AL_A_CARTE:
                builder = new AlaCarteOrderBuilder();
                break;
            default:
                builder = new StandardOrderBuilder();
        }
        return builder;
    }

}
