package chapter08;

import java.util.*;

/**
 * In this solution, the main thread waits for the objectToSync object until the database loading thread is done. Once
 * the database loading thread is done, it notifies the objectToSync that whomever is waiting on it can continue executing.
 * The same process happens when loading the orders into your system. The main thread waits on the objectToSync until the
 * orders loading thread notifies the objectToSync to continue by calling the objectToSync.notify() method. After
 * ensuring that both the inventory and the orders are loaded, the main thread executes the processOrder() method to
 * process all orders.
 */
public class Recipe8_7_1 {

    private int result;

    public static void main(String[] args) {
        Recipe8_7_1 recipe = new Recipe8_7_1();
        recipe.start();
    }

    Random random = new Random();
    List<String> itemList = new ArrayList<>();
    Map<String,Integer> inventoryMap = new HashMap<>();
    Collection<Order> orderList = new ArrayList<>();

    private final Object objectToSync = new Object();

    private void start() {
        loadItems();

        Thread inventoryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading inventory from database...");
                loadInventory();
                synchronized (objectToSync) {
                    objectToSync.notify();
                }
            }
        });

        synchronized (objectToSync) {
            inventoryThread.start();
            try {
                /**
                 *  By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to
                 *  sleep. A thread might do this if it needs to wait for something to happen in another part of the
                 *  application, as we'll see shortly. Later, when the necessary event happens, the running thread calls
                 *  notify() from a block synchronized on the same object. The first thread wakes up and tries to acquire
                 *  the lock again. When the first thread manages to reacquire the lock, it continues from where it left
                 *  off. However, the thread that was waiting may not get the lock immediately (or perhaps ever). It
                 *  depends on when the second lock eventually releases the lock and which thread manages to snag it next.
                 *  The first thread won't wake up from the wait() unless another thread call notify(). An overloaded
                 *  version of wait(), however, allows us to specify a timeout period. If another thread does not call
                 *  notify in the specified period, the waiting thread automatically wakes up. Learning Java
                 *
                 */
                objectToSync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread ordersThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Loading orders from XML Web Service...");
                loadOrders();
                synchronized (objectToSync) {
                    objectToSync.notify();
                }
            }
        });

        synchronized (objectToSync) {
            ordersThread.start();
            try {
                objectToSync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        processOrders();
    }

    private void processOrders() {
        for (Order order : orderList) {
            boolean fulfillable = canFulfill(order);
            if (fulfillable) {
                doFulfill(order);
                System.out.println("Order # " + String.valueOf(order.getOrderId()) + " has been fulfilled.");
            } else {
                System.out.println("Order # " + String.valueOf(order.getOrderId()) + " CANNOT be fulfilled.");
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
            if (quantity > currentInventory) {
                return false;
            }
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
        private Map<String,Integer> orderedItems = new HashMap<>();

        Order(long orderId) { this.orderId = orderId; }

        public void addItem(String itemName, int quantity) {
            Integer currentQuantity = orderedItems.get(itemName);
            if (currentQuantity == null) {
                currentQuantity = 0;
            }
            orderedItems.put(itemName, currentQuantity + quantity);
        }

        public Map<String, Integer> getOrderedItems() { return orderedItems; }

        public long getOrderId() { return orderId; }
    }

}
