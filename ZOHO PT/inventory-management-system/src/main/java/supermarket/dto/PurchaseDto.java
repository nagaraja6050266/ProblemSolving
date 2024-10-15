package supermarket.dto;

import supermarket.models.Purchase;
import supermarket.models.PurchaseLineItem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PurchaseDto extends Purchase {
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public PurchaseDto(String supplierName) {
        this.supplierName = supplierName;
    }

    public PurchaseDto(){}

    public PurchaseDto(int purchaseId, int supplierId, int warehouseId, List<PurchaseLineItem> purchaseItems, float totalAmount, Date purchaseDate, String supplierName) {
        super(purchaseId, supplierId, warehouseId, purchaseItems, totalAmount, purchaseDate);
        this.supplierName = supplierName;
    }

    public PurchaseDto(ResultSet rs) throws SQLException {
        super(rs);
        this.supplierName = rs.getString("supplierName");
    }
}
