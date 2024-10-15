package supermarket.dto;

import jakarta.json.bind.annotation.JsonbNillable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonbNillable(value = true)
public class BatchDto {
    private int batchId;
    private int itemId;
    private String itemName;
    private float quantity;
    private Date expiryDate;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BatchDto() {
    }


    public BatchDto(int batchId, int itemId, String itemName, float quantity, Date expiryDate) {
        this.batchId = batchId;
        this.expiryDate = expiryDate;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public BatchDto(ResultSet rs) throws SQLException {
        this.batchId=rs.getInt("batchId");
        this.expiryDate=rs.getDate("expiryDate");
        this.itemId=rs.getInt("itemId");
        this.itemName=rs.getString("itemName");
        this.quantity=rs.getFloat("quantity");
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

}
