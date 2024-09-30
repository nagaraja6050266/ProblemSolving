package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersDao {

    public Customer addCustomer(Customer customer) {
        customer.setId(Database.getCustomers().size() + 1);
        Database.getCustomers().put(customer.getId(), customer);
        return customer;
    }

    public Customer getCustomerById(int id) {
        if (Database.getCustomers().containsKey(id)) {
            return Database.getCustomers().get(id);
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(Database.getCustomers().values());
    }

    public Customer editCustomer(int id, Customer customer) {
        if (Database.getCustomers().containsKey(id)) {
            customer.setId(id);
            Database.getCustomers().put(id, customer);
            return customer;
        }
        return null;
    }

    public Customer deleteCustomer(int id) {
        if (Database.getCustomers().containsKey(id)) {
            return Database.getCustomers().remove(id);
        }
        return null;
    }

}
