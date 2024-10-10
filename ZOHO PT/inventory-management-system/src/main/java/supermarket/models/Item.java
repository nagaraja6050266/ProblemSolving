package supermarket.models;

public class Item {
    private int itemId;
    private String name;
    private float quantity = 0;
    private int categoryId;
    private float costPrice;
    private float sellingPrice;
    private int expirationDays;

    public Item() {
    }

    public Item(int itemId,String name, float quantity, int categoryId, float costPrice, float sellingPrice, int expirationDays) {
        this.itemId=itemId;
        this.name = name;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.expirationDays = expirationDays;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
    }

}
