package supermarket.dao;

public class DaoDistributor {
    private static CustomersDao customersDao;
    private static ItemsDao itemsDao;

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

}

