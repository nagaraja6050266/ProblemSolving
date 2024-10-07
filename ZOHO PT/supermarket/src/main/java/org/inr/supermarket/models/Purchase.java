package org.inr.supermarket.models;

import org.inr.supermarket.dao.ItemsDao;

public class Purchase {

    private int invoiceId;
    private int itemId;
    private float quantity;
    private float amount;
    private int purchaseId;

    ItemsDao itemsDao = new ItemsDao();

    public Purchase() {
        System.out.println("argument less purchase");
    }

    public void Purchase(int itemId, float quantity, float amount) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Purchase(int invoiceId, int itemId, float quantity, float amount, int purchaseId) {
        this.invoiceId = invoiceId;
        Purchase(itemId,quantity,amount);
        this.purchaseId = purchaseId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
