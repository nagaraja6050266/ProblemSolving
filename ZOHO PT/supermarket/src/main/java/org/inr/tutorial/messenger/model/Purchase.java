package org.inr.tutorial.messenger.model;

import org.inr.tutorial.messenger.dao.ItemsDao;
import org.inr.tutorial.messenger.database.Database;

import java.sql.SQLException;

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

    public Purchase(int itemId, float quantity) {
        setItemId(itemId);
        setQuantity(quantity);
        try {
            this.amount = itemsDao.getItemById(itemId).getPrice() * quantity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("argumented purchase");
    }

    public float getQuantity() {
        return quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(float quantity) {
        Item currentItem = null;
        try {
            currentItem = itemsDao.getItemById(itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (currentItem.getQuantity() < quantity) {
            System.out.println("Out of Stock");
            this.quantity = 0;
            return;
        }
        currentItem.setQuantity(currentItem.getQuantity() - quantity);
        this.quantity = quantity;
        try {
            amount = itemsDao.getItemById(itemId).getPrice() * quantity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public float getAmount() {
        return amount;
    }

}
