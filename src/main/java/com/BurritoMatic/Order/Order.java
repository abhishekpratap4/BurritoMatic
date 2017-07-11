package com.BurritoMatic.Order;

import com.BurritoMatic.Dish;
import com.BurritoMatic.Ingredient;
import com.BurritoMatic.IngredientCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratap on 4/30/17.
 */
public class Order {

    private Dish orderDish;
    private List<Ingredient> order;

    public Dish getOrderDish() {
        return orderDish;
    }

    public List<Ingredient> getOrder() {
        return order;
    }

    public Order(Dish dish) {
        orderDish = dish;
        order = new ArrayList<>();
    }

    public void appendToOrder(IngredientCategory category, String ingChoice) {
        order.add(new Ingredient(category, ingChoice));
    }

    @Override
    public String toString() {
        StringBuilder orderBuilder = new StringBuilder("\nOrder Summary:\n");
        orderBuilder.append("Dish: ").append(orderDish.toString()).append("\nIngredients: ");
        for (Ingredient i : order) {
            orderBuilder.append(i.toString()).append("\t");
        }
        return orderBuilder.toString();
    }
}

