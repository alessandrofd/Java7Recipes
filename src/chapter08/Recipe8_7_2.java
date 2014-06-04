package chapter08;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * In the following code a CountDownLatch with the initial value of 2 is created; then the two threads for loading the
 * inventory and loading the order information are created and started. As each of the two threads finish executing, they
 * call the CountDownLatch's countDown() method, which decrements the latch's value by one. The main thread waits until
 * the CountDownLatch reaches 0, at which point it resumes execution.
 */
public class Recipe8_7_2 {

    Random random = new Random();
    List<String> itemList = new ArrayList<>();
    Collection<Order> orderList = new ArrayList<>();
    Map<String, Integer> inventoryMap = new HashMap<>();

    public static void main(String[] args) {
        Recipe8_7_2 recipe = new Recipe8_7_2();
        recipe.start();
    }

    CountDownLatch latch = new CountDownLatch(2);

    public void start() {
        loadItems();

        Thread inventoryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading inventory from database...");
                loadInventory();
                latch.countDown();
            }
        });

        inventoryThread.start();

        Thread ordersThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading orders from XML web service...");
                loadOrders();
                latch.countDown();
            }
        });

        ordersThread.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processOrders();
    }

    private void processOrders() {
        for (Order order : orderList) {
            boolean fulfillable = canFulfill(order);
            if (fulfillable) {
                doFulfill(order);
                System.out.println("Order #" + String.valueOf(order.getOrderId()) + " has been fulfilled.");
            } else {
                System.out.println("Order #" + String.valueOf(order.getOrderId()) + " CANNOT be fulfilled.");
            }
        }
    }

    private void doFulfill(Order order) {
        for (String item : order.getOrderedItems().keySet()) {
            int quantity = order.getOrderedItems().get(item);
            int currentInventory = inventoryMap.get(item);
            inventoryMap.put(item, currentInventory - quantity);
        }
    }

    private boolean canFulfill(Order order) {
        for (String item : order.getOrderedItems().keySet()) {
            int quantity = order.getOrderedItems().get(item);
            int currentInventory = inventoryMap.get(item);
            if (currentInventory < quantity)
                return false;
        }
        return true;
    }

    private void loadOrders() {
        for (int i = 0; i < 1000; i++) {
            Order order = new Order(i);
            for (int j = 0; j < 10; j++) {
                String randomItem = itemList.get(random.nextInt(itemList.size()));
                order.addItem(randomItem, random.nextInt(2) + 1);
            }
            orderList.add(order);
        }
    }

    private void loadItems() {
        for (int i = 0; i < 100; i++) {
            itemList.add("Item #" + i);
        }
    }

    private void loadInventory() {
        for (String item : itemList) {
            inventoryMap.put(item, random.nextInt(500));
        }
    }

    class Order {
        long orderId;
        Map<String,Integer> orderedItems = new HashMap<>();

        Order(long orderId) {
            this.orderId = orderId;
        }

        public long getOrderId() {
            return orderId;
        }

        public Map<String, Integer> getOrderedItems() {
            return orderedItems;
        }

        public void  addItem(String itemName, int quantity) {
            Integer currentQuantity = orderedItems.get(itemName);
            if (currentQuantity == null)
                currentQuantity = 0;
            orderedItems.put(itemName, currentQuantity + quantity);
        }
    }
}
