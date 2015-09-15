package me.bdav.java.shopexample;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Basket of products.
 */
public class Basket {

    /*
     *  Hashmap of Product UPC (preserving leading 0s) against quantity.
     */
    private ConcurrentHashMap<String, Integer> basketContents;

    /*
     *  Shop in which basket resides.
     */
    private Shop shop;

    /*
     *  Constructor. Sets reference to domicile shop.
     */
    public Basket(Shop shop) {
        basketContents = new ConcurrentHashMap<>();
        this.shop = shop;
    }

    /*
     * Add one product to basket.
     * @param upc   UPC of product
     */
    public void addProduct(String upc) {
        synchronized (basketContents) {
            Integer quantity = basketContents.get(upc);

            if (quantity == null)
                quantity = 1;
            else
                quantity += 1;

            basketContents.put(upc, quantity);

        }
    }

    /*
     * Remove one product from basket.
     * @param upc   UPC of product
     */
    public void removeProduct(String upc) {
        synchronized (basketContents) {
            Integer quantity = basketContents.get(upc);

            if (quantity != null) {
                if (quantity > 1) {
                    quantity -= 1;
                    basketContents.put(upc, quantity);
                } else
                    basketContents.remove(upc);
            }

        }
    }

    /*
    * Get total cost of basket in lowest denomination
    */
    public int getTotalCost() {
        int totalCost = 0;

        synchronized (basketContents) {
            for (String upc : basketContents.keySet()) {
                totalCost += (basketContents.get(upc) * shop.getProduct(upc).getPrice());
            }
        }

        return totalCost;
    }
}
