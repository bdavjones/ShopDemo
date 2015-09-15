package me.bdav.java.shopexample.tests;

import me.bdav.java.shopexample.Basket;
import me.bdav.java.shopexample.Product;
import me.bdav.java.shopexample.Shop;


import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for shopexample
 */
public class ShopTestSuite {

    /**
     * Create example shop with proviided products. Utility method for tests.
     */
    public Shop createExampleShop() {
        Shop shop = new Shop();

        Product banana = new Product("Banana", 75);
        Product orange = new Product("Orange", 67);
        Product apple = new Product("Apple", 55);
        Product lemon = new Product("Lemon", 120);
        Product peach = new Product("Peach", 99);

        shop.addProduct("00001", banana);
        shop.addProduct("00002", orange);
        shop.addProduct("00003", apple);
        shop.addProduct("00004", lemon);
        shop.addProduct("00005", peach);

        return shop;
    }


    /**
     * Test a simple shop instance.
     */
    @Test
    public void shopTest() {

        // Shop instance to test
        Shop shop = createExampleShop();

        assertEquals("Shop should contain 5 items", 5, shop.getProductCount());

    }



    /**
     * Test a single basket.
     */
    @Test
    public void basketTest() {
        Shop shop = createExampleShop();
        Basket basket = new Basket(shop);

        basket.addProduct("00001");
        basket.addProduct("00004");
        basket.addProduct("00001");
        basket.addProduct("00001");

        basket.removeProduct("00001");
        basket.removeProduct("00007"); //Attempt to remove non-existant product. Should not change total.

        assertEquals("Basket should contain 270p of products", 270, basket.getTotalCost());

    }

    /**
     *  Test concurrent access to a basket.
     */
    @Test
    public void concurrentBasketTest() {
        Shop shop = createExampleShop();
        Basket basket = new Basket(shop);


        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String upc = "0000" + (random.nextInt(5) +1);
            BasketTestThread thread = new BasketTestThread(upc, basket);
            thread.run();
        }

        assertEquals("Basket should contain 0p of products", 0, basket.getTotalCost());
    }



    /**
     * Test thread to add an item to the basket, wait a random time between 0 and 1 secconds, then
     * remove the item in order to test concurrent access.
     */
    class BasketTestThread extends Thread {

        final String upc;
        final Basket basket;

        public BasketTestThread (String upc, Basket basket) {
            this.upc = upc;
            this.basket = basket;
        }

        public void run() {
            System.out.println("Basket Test Thread for UPC: "+ upc);

            basket.addProduct(upc);

            //Wait for 0-1sec
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                fail("Thread Interrupted.");
            }

            basket.removeProduct(upc);
        }
    }
}

