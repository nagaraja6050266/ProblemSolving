package org.inr.tutorial.messenger.model;

import org.inr.tutorial.messenger.database.Database;

import java.util.*;

public class Invoice {
    private int id;
    private int customerId;
    private List<Purchase> purchases = new ArrayList<>();
    private Date date;
    private float totalAmount = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            this.totalAmount += purchase.getAmount();
        }
        this.purchases = purchases;
    }

    public Invoice() {
        System.out.println("no argumented");
        this.date = new Date();
    }

    public Invoice(int id, int customerId, Date date, float totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.totalAmount = totalAmount;
    }


//    public void addItemToInvoice(int itemId, float quantity) {
//        if (purchases.containsKey(itemId)) {
//            Purchase currentPurchase=purchases.get(itemId);
//            currentPurchase.setQuantity(currentPurchase.getQuantity()+quantity);
//        } else {
//            purchases.put(itemId, new Purchase(itemId,quantity));
//        }
//    }
//
//    public void removeItemFromInvoice(int itemId, float quantity) {
//        if (purchases.containsKey(itemId)) {
//            Purchase existingPurchase = purchases.get(itemId);
//            if (existingPurchase.getQuantity() < quantity) {
//                purchases.remove(itemId);
//            } else {
//                existingPurchase.setQuantity(existingPurchase.getQuantity()-quantity);
//            }
//        } else {
//            System.out.println("No need to remove its already not there");
//        }
//    }

//    public String purchaseDetails() {
//        String purchaseDetails = "";
//        for (Map.Entry<Integer, Purchase> purchase : purchases.entrySet()) {
//            purchaseDetails += "\nItem: " + Database.getItems().get(purchase.getKey()).getName() + "\nQuantity: " + purchase.getValue();
//        }
//        return purchaseDetails;
//    }
//
//    @Override
//    public String toString() {
//        return "\n\tInvoice\nInvoice ID: " + id + "\nCustomer Name: " + Database.getCustomers().get(customerId).getName() + "\nPurchase Date: " + date + "\n" + purchaseDetails();
//    }
}
