
package model;

public class Product {
    private int id;
    private String name;
    private String brand;
    private String description;
    private int discount;
    private int price;
    private String image;
    
    public Product() {
    }

    public Product(int id, String name, String brand, String description, int discount, int price, String image) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.discount = discount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
