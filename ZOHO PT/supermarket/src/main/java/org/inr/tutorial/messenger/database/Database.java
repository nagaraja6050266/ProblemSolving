package org.inr.tutorial.messenger.database;

import org.inr.tutorial.messenger.model.Customer;
import org.inr.tutorial.messenger.model.Item;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Map<Integer, Item> items = new HashMap<>();
    private static Map<Integer, Customer> customers = new HashMap<>();

    static {
        Item milk = new Item("Milk", 3);
        milk.setId(1);
        Item curd = new Item("Curd", 4);
        curd.setId(2);
        items.put(1, milk);
        items.put(2, curd);
    }

    static {
        Customer nagaraja = new Customer("Nagaraja", "9876543219");
        nagaraja.setId(1);
        Customer eeshu = new Customer("Eeshu", "9876543210");
        eeshu.setId(2);
        customers.put(1, nagaraja);
        customers.put(2, eeshu);
    }

    public static Map<Integer, Item> getItems() {
        return items;
    }

    public static Map<Integer, Customer> getCustomers() {
        return customers;
    }
}


//public class Database {
//
//    private static final String URL = "jdbc:mysql://localhost:3306/supermarket";
//    private static final String USER = "root";
//    private static final String PASSWORD = "12345678";
//
//
//    public static Connection getConnection(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected with Driver");
//            return connection;
//        } catch (ClassNotFoundException e) {
//            System.out.println("Error Connecting with driver");
//            throw new RuntimeException(e);
//        } catch (Exception e){
//            System.out.println("Connection Error");
//            throw new RuntimeException(e);
//        }
//    }
//}
