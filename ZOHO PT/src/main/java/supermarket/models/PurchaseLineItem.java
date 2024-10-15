package supermarket.models;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.sql.Date;

public class PurchaseLineItem extends LineItem {
    @JsonbDateFormat("dd-MM-yyyy")
    private Date expiryDate;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
