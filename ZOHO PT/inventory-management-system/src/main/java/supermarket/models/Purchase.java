package supermarket.models;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbNillable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@JsonbNillable(value = false)
public class Purchase{
    private int purchaseId;
    private int supplierId;
    private int warehouseId;
    private List<PurchaseLineItem> purchaseItems=new ArrayList<>();
    private float totalAmount;

    @JsonbDateFormat("dd-MM-yyyy")
    private Date purchaseDate;

    public Purchase() {
    }

    public Purchase(int purchaseId, int supplierId, int warehouseId, List<PurchaseLineItem> purchaseItems, float totalAmount, Date purchaseDate) {
        this.purchaseId = purchaseId;
        this.supplierId = supplierId;
        this.warehouseId=warehouseId;
        this.purchaseItems = purchaseItems;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }

    public Purchase(ResultSet rs) throws SQLException {
        this.purchaseId=rs.getInt("purchaseId");
        this.supplierId=rs.getInt("supplierId");
        this.totalAmount=rs.getInt("totalAmount");
        this.warehouseId=rs.getInt("warehouseId");
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<PurchaseLineItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseLineItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public float calculateTotalAmount(){
        float total=0;
        for(PurchaseLineItem lineItem:purchaseItems){
            total+=lineItem.getAmount();
        }
        this.totalAmount=total;
        return total;
    }

    public void addItem(PurchaseLineItem lineItem){
        this.purchaseItems.add(lineItem);
    }

}