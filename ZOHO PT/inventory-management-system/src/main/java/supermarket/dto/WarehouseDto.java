package supermarket.dto;

import supermarket.models.Warehouse;

import java.util.List;

public class WarehouseDto extends Warehouse {
    private List<BatchDto> batches;

    public List<BatchDto> getBatches() {
        return batches;
    }

    public void setBatches(List<BatchDto> batches) {
        this.batches = batches;
    }

    public void addBatch(BatchDto batch){
        this.batches.add(batch);
    }

    public WarehouseDto(Warehouse warehouse, List<BatchDto> batches){
        super(warehouse.getWarehouseId(),warehouse.getName(),warehouse.getLocation(),warehouse.getCapacity());
        this.batches=batches;
    }

    public WarehouseDto(){
    }

}
