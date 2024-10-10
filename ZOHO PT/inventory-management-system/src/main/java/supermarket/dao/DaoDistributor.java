package supermarket.dao;

public class DaoDistributor {
    private static CustomersDao customersDao;
    private static CategoriesDao categoriesDao;
    private static ItemsDao itemsDao;
    private static WarehousesDao warehousesDao;

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

}

