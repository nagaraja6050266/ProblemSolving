package supermarket.models;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String phNumber;

    public Supplier(){}

    public Supplier(int supplierId, String supplierName, String phNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phNumber = phNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
