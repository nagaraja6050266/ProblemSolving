package supermarket.models;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.sql.Date;
import java.util.List;

public class Purchase{
    private int purchaseId;
    private int supplierId;
    private int warehouseId;
    private List<PurchaseLineItem> purchaseItems;
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

}