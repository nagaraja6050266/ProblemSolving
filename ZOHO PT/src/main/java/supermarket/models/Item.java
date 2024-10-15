package supermarket.models;

public class Item {
    private int itemId;
    private String name;
    private int categoryId;
    private float costPrice;
    private float sellingPrice;

    public Item() {
    }

    public Item(int itemId,String name, int categoryId, float costPrice, float sellingPrice) {
        this.itemId=itemId;
        this.name = name;
        this.categoryId = categoryId;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
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

}
