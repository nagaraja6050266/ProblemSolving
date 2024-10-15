package supermarket.dto;

import supermarket.models.Item;
import supermarket.models.Warehouse;

import java.util.List;

public class WarehouseDto extends Warehouse {
    private List<ItemDto> items;

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public WarehouseDto(){
    }

    public WarehouseDto(Warehouse warehouse,List<ItemDto> items){
        super(warehouse.getWarehouseId(),warehouse.getName(),warehouse.getLocation(),warehouse.getCapacity());
        this.items=items;
    }

}
