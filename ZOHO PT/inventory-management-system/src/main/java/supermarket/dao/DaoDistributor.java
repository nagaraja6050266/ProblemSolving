package supermarket.dao;

import java.sql.SQLException;

public class DaoDistributor {
    private static CustomersDao customersDao;
    private static CategoriesDao categoriesDao;
    private static ItemsDao itemsDao;
    private static WarehousesDao warehousesDao;
    private static SuppliersDao suppliersDao;
    private static BatchesDao batchesDao;
    private static PurchasesDao purchasesDao;
    private static SalesDao salesDao;

    public static SalesDao getSalesDao() throws SQLException {
        if(salesDao==null){
            salesDao=new SalesDao();
        }
        return salesDao;
    }

    public static PurchasesDao getPurchasesDao() throws SQLException {
        if(purchasesDao==null){
            purchasesDao=new PurchasesDao();
        }
        return purchasesDao;
    }

    public static CustomersDao getCustomersDao() {
        if (customersDao == null) {
            customersDao = new CustomersDao();
        }
        return customersDao;
    }

    public static ItemsDao getItemsDao() {
        if (itemsDao == null) {
            itemsDao = new ItemsDao();
        }
        return itemsDao;
    }

    public static CategoriesDao getCategoriesDao(){
        if(categoriesDao==null) {
            categoriesDao = new CategoriesDao();
        }
        return categoriesDao;
    }

    public static WarehousesDao getWarehousesDao(){
        if(warehousesDao==null){
            warehousesDao = new WarehousesDao();
        }
        return warehousesDao;
    }

    public static SuppliersDao getSuppliersDao(){
        if(suppliersDao==null){
            suppliersDao=new SuppliersDao();
        }
        return suppliersDao;
    }

    public static BatchesDao getBatchesDao() throws SQLException {
        if(batchesDao==null){
            batchesDao=new BatchesDao();
        }
        return batchesDao;
    }
}

