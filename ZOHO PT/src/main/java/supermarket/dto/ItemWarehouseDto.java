package supermarket.dto;

public class ItemWarehouseDto extends ItemDto{
    private int warehouseId;

    public ItemWarehouseDto(){
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
}
