package supermarket.dto;

import jakarta.json.bind.annotation.JsonbNillable;

import java.sql.ResultSet;
import java.sql.SQLException;

@JsonbNillable(value = false)
public class ExpiredBatch extends BatchDto{
    private int warehouseId;
    private String warehouseName;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public ExpiredBatch(ResultSet rs) throws SQLException {
        super(rs);
        this.warehouseId = rs.getInt("warehouseId");
        this.warehouseName = rs.getString("warehouseName");
        this.reason=rs.getString("reason");
    }

    public ExpiredBatch(){
        super();
    }

}
