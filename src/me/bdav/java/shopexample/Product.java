package me.bdav.java.shopexample;


/**
 * Represents a saleable item in a single currency.
 */
public class Product {

    private String productName;
    private volatile int price;


    /**
     * Constructor for a product with name and price
     * @param productName   Name of product
     * @param price         Product price in lowest denomination
     */
    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }


    /**
     * Set Product Name.
     * @param productName   Name of product
     */
    public void setName(String productName) {
        this.productName = productName;
    }

    /**
     * Set  price.
     * @param price   price of product as a decimal
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Get  price in lowest denomination.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get  Name.
     */
    public String getProductName() {
        return productName;
    }
}
