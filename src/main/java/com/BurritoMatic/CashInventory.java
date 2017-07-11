package com.BurritoMatic;

/**
 * Created by pratap on 4/30/17.
 */

// Implemented as a Singelton as I have assumed that there will be only 1 cash inventory instance
public class CashInventory {
    private float amount;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    private CashInventory() {
    }

    private static class CashInventorySingeltonHelper {
        private static final CashInventory cashInventory = new CashInventory();
    }

    public static CashInventory getCashInventoryInstance() {
        return CashInventorySingeltonHelper.cashInventory;
    }
}
