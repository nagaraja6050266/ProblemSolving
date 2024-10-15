package supermarket.models;

public class Customer {
    private int customerId;
    private String customerName;
    private String phNumber;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String phNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phNumber = phNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
