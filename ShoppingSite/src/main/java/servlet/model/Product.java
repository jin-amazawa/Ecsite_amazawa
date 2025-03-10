package servlet.model;

public class Product {
    private int product_id;
    private String name;
    private int price;
    private String description;
    private String image_Path;
    private int quantity;
    
    // デフォルトコンストラクタ（引数なし）
    public Product() {}
    
    
    public Product(int id, String name, int price, String description, String image_Path) {
        this.product_id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image_Path = image_Path;
        this.quantity = 1;
    }

    // getter
    public int getId() {
        return product_id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image_Path;
    }
    public int getQuantity() {
        return quantity;
    }
    
    
    
    // setter
    public void setId(int id) {
        this.product_id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImage(String image_Path) {
        this.image_Path = image_Path;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
