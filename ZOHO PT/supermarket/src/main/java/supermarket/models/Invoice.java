package org.inr.supermarket.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private int id;
    private int customerId;
    private List<Purchase> purchases = new ArrayList<>();
    private Date date;
    private float totalAmount = 0;
    private InvoiceStatus invoiceStatus;

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
        this.totalAmount = 0;
        for (Purchase purchase : purchases) {
            this.totalAmount += purchase.getAmount();
        }
        this.purchases = purchases;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Invoice() {
        this.date = new Date();
    }

    public Invoice(int id, int customerId, Date date, float totalAmount,InvoiceStatus invoiceStatus) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.totalAmount = totalAmount;
        this.invoiceStatus=invoiceStatus;
    }
}
