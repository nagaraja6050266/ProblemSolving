package org.inr.tutorial.messenger.model;

import org.inr.tutorial.messenger.database.Database;

public class Purchase {
    private int itemId;
    private float quantity;
    private float amount;

    public Purchase() {
        System.out.println("argument less purchase");
    }

    public float getQuantity() {
        return quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public Purchase(int itemId, float quantity) {
        setItemId(itemId);
        setQuantity(quantity);
        this.amount = Database.getItems().get(itemId).getPrice() * quantity;
        System.out.println("argumented purchase");
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(float quantity) {
        Item currentItem = Database.getItems().get(itemId);
        if (currentItem.getQuantity() < quantity) {
            System.out.println("Out of Stock");
            this.quantity = 0;
            return;
        }
        currentItem.setQuantity(currentItem.getQuantity() - quantity);
        this.quantity = quantity;
        amount = Database.getItems().get(itemId).getPrice() * quantity;
    }

    public float getAmount() {
        return amount;
    }

}
