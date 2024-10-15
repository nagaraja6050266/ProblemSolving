package supermarket.dto;

import supermarket.models.Item;

public class ItemDto extends Item {

    private float quantity;

    public ItemDto(){
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public ItemDto(Item item, float quantity){
        super(item.getItemId(),item.getName(),item.getCategoryId(),item.getCostPrice(),item.getSellingPrice());
        this.quantity=quantity;
    }

}
