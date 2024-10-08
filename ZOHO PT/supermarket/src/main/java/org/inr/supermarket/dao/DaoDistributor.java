package org.inr.supermarket.dao;

public class DaoDistributor {
    private static CustomersDao customersDao;
    private static ItemsDao itemsDao;
    private static InvoicesDao invoicesDao;
    private static PurchasesDao purchasesDao;
    private static PaymentsDao paymentsDao;

    // Lazy Initialization for CustomersDao
    public static CustomersDao getCustomersDao() {
        if (customersDao == null) {
            customersDao = new CustomersDao();
        }
        return customersDao;
    }

    // Lazy Initialization for ItemsDao
    public static ItemsDao getItemsDao() {
        if (itemsDao == null) {
            itemsDao = new ItemsDao();
        }
        return itemsDao;
    }

    // Lazy Initialization for InvoicesDao
    public static InvoicesDao getInvoicesDao() {
        if (invoicesDao == null) {
            invoicesDao = new InvoicesDao();
        }
        return invoicesDao;
    }

    // Lazy Initialization for PurchasesDao
    public static PurchasesDao getPurchasesDao() {
        if (purchasesDao == null) {
            purchasesDao = new PurchasesDao();
        }
        return purchasesDao;
    }

    // Lazy Initialization for PaymentsDao
    public static PaymentsDao getPaymentsDao() {
        if (paymentsDao == null) {
            paymentsDao = new PaymentsDao();
        }
        return paymentsDao;
    }
}

