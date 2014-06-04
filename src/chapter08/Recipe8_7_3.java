package chapter08;

import java.util.*;

/**
 * The following example has a thread for loading the inventory and another thread for loading the orders. Once each
 * thread is started, a call to inventoryThread.join() will make the main thread wait for the inventoryThread to finish
 * before continuing.
 */
public class Recipe8_7_3 {

    Random random = new Random();
    Map<String,Integer> inventoryMap = new HashMap<>();
    List<String> itemList = new ArrayList<>();
    Collection<Order> orderList = new ArrayList<>();

    public static void main(String[] args) {
        Recipe8_7_3 recipe = new Recipe8_7_3();
        recipe.start();
    }

    private void start() {
        loadItems();

        Thread inventoryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading inventory from database...");
                loadInventory();
            }
        });

        inventoryThread.start();
        try {
            inventoryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread ordersThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading orders from XML web service...");
                loadOrders();
            }
        });

        ordersThread.start();
        try {
            ordersThread.join();
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
            if (quantity > currentInventory)
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
            inventoryMap.put(item, random.nextInt(5000));
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

        public void addItem(String itemName, int quantity) {
            Integer currentQuantity = orderedItems.get(itemName);
            if (currentQuantity == null)
                currentQuantity = 0;
            orderedItems.put(itemName, currentQuantity + quantity);
        }
    }
}
