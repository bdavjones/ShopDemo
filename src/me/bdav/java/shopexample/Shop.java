package me.bdav.java.shopexample;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains a map of products and their prices and a set of any active baskets.
 */
public class Shop {

    /** Hashmap of UPC (preserving leading 0s) against Product objects
     */
    private ConcurrentHashMap<String, Product> productCatalogue;

    /**
     * Default constructor. Instantiates product catalog.
     */
    public Shop() {
        productCatalogue = new ConcurrentHashMap<>();
    }

    /**
     * Adds or Replaces a product in the catalogue.
     *
     * @param upc       The UPC or Product Number
     * @param product   The product object to be added
     */
    public void addProduct(String upc, Product product) {
        productCatalogue.put(upc, product);
    }


    /**
     * Remove a product from the catalogue.
     *
     * @param upc       The UPC or Product Number
     * @return          True if product exists (and thus removed)
     */
    public boolean removeProduct(String upc) {
        return (productCatalogue.remove(upc) != null);
    }

    /**
     * Get a product from the catalogue by UPC.
     */
    public Product getProduct(String upc) {
        return productCatalogue.get(upc);
    }

    /**
     * Get count of products
     *
     * @return          Number of products stored.
     */
    public int getProductCount() {
        return productCatalogue.size();
    }
}
