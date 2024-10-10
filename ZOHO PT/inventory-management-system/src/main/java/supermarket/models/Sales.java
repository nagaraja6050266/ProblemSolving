package supermarket.models;

import java.sql.Date;
import java.util.List;

public class Sales {
    private int salesId;
    private Date date;
    private List<LineItem> salesItems;
    private int customerId;
    private float totalAmount;

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<LineItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<LineItem> salesItems) {
        this.salesItems = salesItems;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

}
