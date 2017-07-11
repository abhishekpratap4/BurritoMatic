package com.BurritoMatic;

import com.BurritoMatic.Order.Order;
import com.BurritoMatic.Order.AbstractOrderBuilder;
import com.BurritoMatic.Order.OrderBuilderFactory;
import com.BurritoMatic.exception.InSufficientIngredientsInInventoryException;
import com.BurritoMatic.exception.InvalidIngredientsSelectedException;
import com.BurritoMatic.validator.Validator;
import com.BurritoMatic.validator.ValidatorFactory;

import java.util.Optional;
import java.util.Scanner;

public class BurritoMatic {

    private IngredientInventory ingredientInventory;
    private CashInventory cashInventory;
    private Menu menu;

    public BurritoMatic() {
        ingredientInventory = IngredientInventory.getIngredientInventoryInstance();
        cashInventory = CashInventory.getCashInventoryInstance();
        menu = Menu.getMenuInstance();
    }

    public static void main(String[] args) {
        BurritoMatic burritoMatic = new BurritoMatic();
        System.out.println("Welcome to BurritoMatic! \n");
        burritoMatic.getUserBurritoOrder();
    }

    private void getUserBurritoOrder() {
        Order order = null;
        do {
            System.out.println("\nPick your Burrito and enter its number to continue!\n");
            menu.printMenu();
            System.out.println("Press 0 to Exit");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == 0)
                break;
            Optional<Dish> userDish = menu.isUserBurritoChoiceValid(userChoice);
            if (userDish.isPresent()) {
                order = getOrderIngredients(scanner, userDish);
            } else {
                System.out.println("\nInvalid Burrito option selected. Please choose a Burrito from the below list.");
                menu.printMenu();
            }
            if (order != null) {
                System.out.println(order);

                Validator validator = new ValidatorFactory().getValidator(order.getOrderDish());
                if (validator != null) {
                    try {
                        if (validator.validate(order)) {
                            buildBurritoOrder(order);
                        }
                    } catch (InvalidIngredientsSelectedException e) {
                        System.out.println("\nSelect proper ingredients again!");
                    } catch (InSufficientIngredientsInInventoryException e) {
                        System.out.println("\nStock not available, please choose different burrito!");
                    }
                }
            }
        } while (true);
        System.out.println("\nCash IngredientInventory status: $" + cashInventory.getAmount());
        ingredientInventory.printIngredientInventory();
    }

    private void buildBurritoOrder(Order order) throws InSufficientIngredientsInInventoryException {
        OrderBuilderFactory orderBuilderFactory = new OrderBuilderFactory();
        AbstractOrderBuilder builder = orderBuilderFactory.getOrderBuilder(Constants.STANDARD);
        float billAmount = builder.buildOrder(order, ingredientInventory);

        // Marked as synchronized block to avoid multiple thread interference while updating cash inventory
        synchronized (this) {
            cashInventory.setAmount(cashInventory.getAmount() + billAmount);
        }

        System.out.println("Your total bill amount is: $" + billAmount);
    }

    private Order getOrderIngredients(Scanner scanner, Optional<Dish> userDish) {
        Order order = new Order(userDish.get());
        ingredientInventory.printIngredientInventory();
        for (IngredientCategory category : ingredientInventory.getIngredientCategories()) {
            System.out.println("Select ingredient from " + category.toString());
            do {
                String ingChoice = scanner.nextLine();
                if (ingredientInventory.isAvailableInInventory(category, ingChoice)) {
                    order.appendToOrder(category, ingChoice);
                    break;
                } else {
                    System.out.println("Item out of stock. Please select valid ingredients from " + category.toString());
                }
            } while (true);
        }
        return order;
    }
}
