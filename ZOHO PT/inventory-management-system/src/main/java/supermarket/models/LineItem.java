package supermarket.models;


public class LineItem {
    private int itemId;
    private float quantity;
    private float amount;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LineItem(){}

    public LineItem(int itemId, float quantity, float amount) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
    }
}

