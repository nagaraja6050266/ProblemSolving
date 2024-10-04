package org.inr.supermarket.models;

import org.inr.supermarket.dao.ItemsDao;

public class Purchase {
    private int itemId;
    private float quantity;
    private float amount;

    ItemsDao itemsDao = new ItemsDao();

    public Purchase() {
        System.out.println("argument less purchase");
    }

    public Purchase(int itemId, float quantity, float amount) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getItemId() {
        return itemId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getAmount() {
        return amount;
    }

    //Uncaught
    public void setQuantity(float quantity) throws Exception {
        Item currentItem = itemsDao.getItemById(itemId);
        if (currentItem == null) {
            throw new RuntimeException("Item with ID " + itemId + " not found.");
        }
        if (currentItem.getQuantity() < quantity) {
            throw new RuntimeException("Quantity Exceeds the availability");
        }
        currentItem.setQuantity(currentItem.getQuantity() - quantity);
        this.quantity = quantity;
        itemsDao.editItem(currentItem.getId(), currentItem);
        this.amount = currentItem.getPrice() * quantity;
    }
}
