package chapter08;

import java.util.*;

/**
 * Created by Alessandro on 30/03/2014.
 */
public class Recipe8_5_1 {

    public static void main(String[] args) throws InterruptedException {
        Recipe8_5_1 recipe = new Recipe8_5_1();
        recipe.start();
    }

    Set<Thread> orderingThreads = new HashSet<>();
    final Map<String,Integer> inventoryMap = new LinkedHashMap<>();
    List<CustomerOrder> customerOrders = new ArrayList<>();
    Random random = new Random();

    public void start() throws InterruptedException {
        // let's populate our inventory with items
        // at most, we have 20 books
        for (int i = 0; i < 20; i++) {
            inventoryMap.put("Apress book #" + i, 1000);
        }

        // now, let's create ordering threads
        for (int i = 0; i < 20; i++) {
            createOrderingThread();
        }

        // wait a little
        Thread.sleep(100);

        // check on inventory right now
        checkInventoryLevels();

        // wait a little longer
        Thread.sleep(100);

        // stop everything
        for (Thread thread : orderingThreads) {
            thread.interrupt();
        }

        Thread.sleep(1000);

        // check inventory levels again
        checkInventoryLevels();
        // print the orders
        displayOrders();
    }

    private void displayOrders() {
        synchronized (inventoryMap) {
            for (CustomerOrder order : customerOrders) {
                System.out.println(order.getQuantityOrdered() + " " + order.getItemOrdered() + " for " +
                        order.getCustomerName());
            }
        }
    }

    private void createOrderingThread() {
        Thread orderingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    createRandomOrder();
                }
            }
        });
        orderingThread.start();
        orderingThreads.add(orderingThread);
    }

    private void createRandomOrder() {
        String itemOrdered = "Apress book #" + random.nextInt(20);
        int quantityOrdered = random.nextInt(2) + 1;
        String customerName = "Custormer: " + UUID.randomUUID().toString();
        fulfillOrder(itemOrdered, quantityOrdered, customerName);
    }

    private boolean fulfillOrder(String itemOrdered, int quantityOrdered, String customerName) {
        synchronized (inventoryMap) {
            int currentInventory = inventoryMap.get(itemOrdered);
            if (currentInventory < quantityOrdered) {
                System.out.println("Couldn't fulfill order for " + customerName + " not enough " + itemOrdered + " (" +
                    quantityOrdered +")");
                return false; // sorry, we sold out
            }
            inventoryMap.put(itemOrdered, currentInventory - quantityOrdered);
            CustomerOrder order = new CustomerOrder(itemOrdered, quantityOrdered, customerName);
            customerOrders.add(order);
            System.out.println("Order fulfilled for " + customerName + " of " + itemOrdered +
                    " (" + quantityOrdered + ")");
            return true;
        }
    }

    private void checkInventoryLevels() {
        synchronized (inventoryMap) {
            System.out.println("------------------------------------");
            for (Map.Entry<String,Integer> inventoryEntry : inventoryMap.entrySet())
                System.out.println("Inventory Level : " + inventoryEntry.getKey() + " " + inventoryEntry.getValue());
            System.out.println("------------------------------------");
        }
    }

    class CustomerOrder {
        String itemOrdered;
        int quantityOrdered;
        String customerName;

        CustomerOrder(String itemOrdered, int quantityOrdered, String customerName) {
            this.itemOrdered = itemOrdered;
            this.quantityOrdered = quantityOrdered;
            this.customerName = customerName;
        }

        public String getItemOrdered() {
            return itemOrdered;
        }

        public int getQuantityOrdered() {
            return quantityOrdered;
        }

        public String getCustomerName() {
            return customerName;
        }
    }

}
