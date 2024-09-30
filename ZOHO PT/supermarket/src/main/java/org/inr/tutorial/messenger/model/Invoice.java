package org.inr.tutorial.messenger.model;

import org.inr.tutorial.messenger.database.Database;

import java.util.*;

public class Invoice {
    private int id;
    private int customerId;
    private List<Purchase> purchases = new ArrayList<>();
    private Date date;
    private float totalAmount = 0;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPurchases(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            this.totalAmount += purchase.getAmount();
        }
        this.purchases = purchases;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public Invoice() {
        System.out.println("no argumented");
        this.id = Database.getInvoices().size() + 1;
        this.date = new Date();
    }

    public Invoice(int customerId, List<Purchase> purchases) {
        setCustomerId(customerId);
        setPurchases(purchases);
        this.id = Database.getInvoices().size() + 1;
        this.date = new Date();
        System.out.println("Called argumented");
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
