package com.BurritoMatic;

import java.util.Objects;

/**
 * Created by pratap on 4/29/17.
 */
public class Dish {

    private String dishName;
    private int dishId;

    public String getDishName() {
        return dishName;
    }

    public int getDishId() {
        return dishId;
    }

    public Dish(String name, int id) {
        dishName = name;
        dishId = id;
    }

    @Override
    public String toString() {
        return this.dishId + " - " + this.dishName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Dish))
            return false;
        Dish dish = (Dish) obj;
        return Objects.equals(dishName, dish.dishName) && (dishId == dish.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishName, dishId);
    }
}
