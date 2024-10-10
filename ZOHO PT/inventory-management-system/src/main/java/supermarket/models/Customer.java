package supermarket.models;

public class Customer {
    private int customerId;
    private String name;
    private String phNumber;

    public Customer() {
    }

    public Customer(int customerId, String name, String phNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phNumber = phNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
