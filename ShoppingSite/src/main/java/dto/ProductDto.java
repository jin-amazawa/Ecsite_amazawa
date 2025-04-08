package dto;

public class ProductDto {
    private int product_id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String image_Path; 

    // コンストラクタ
    public ProductDto() {}

    public ProductDto(int product_id, String name, String description, int price, int stock, String image_Path) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image_Path = image_Path;
    }

	// Getter & Setter
    public int getId() { return product_id; }
    public void setId(int id) { this.product_id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public String getImagePath() { return image_Path; }
    public void setImagePath(String image_Path) { this.image_Path = image_Path; }
}

