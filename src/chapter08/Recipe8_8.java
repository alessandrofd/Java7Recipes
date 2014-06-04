package chapter08;

/**
 * In the following example, you create an object with getters and setters that are synchronized for each internal variable
 * and you protect the critical regions by using the synchronized(this) lock.
 */
public class Recipe8_8 {

    public static void main(String[] args) {
        Recipe8_8 recipe = new Recipe8_8();
        recipe.start();
    }

    private void start() { }

    class CustomerOrder {
        private String itemOrdered;
        private int quantityOrdered;
        private String customerName;

        public CustomerOrder() { }

        public double calculateOrderTotal(double price) {
            synchronized (this) {
                return getQuantityOrdered() * price;
            }
        }

        public synchronized String getItemOrdered() {
            return itemOrdered;
        }

        public synchronized void setItemOrdered(String itemOrdered) {
            this.itemOrdered = itemOrdered;
        }

        public synchronized int getQuantityOrdered() {
            return quantityOrdered;
        }

        public synchronized void setQuantityOrdered(int quantityOrdered) {
            this.quantityOrdered = quantityOrdered;
        }

        public synchronized String getCustomerName() {
            return customerName;
        }

        public synchronized void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }

    /**
     * In the following code, the internal variables to the object are declared final, and are assigned at construction.
     * By doing so it is guaranteed that the object is immutable.
     */
    class ImmutableCustomerOrder {
        final private String itemOrdered;
        final private int quantityOrdered;
        final private String customerName;

        ImmutableCustomerOrder(String itemOrdered, int quantityOrdered, String customerName) {
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

        public synchronized double calculateOrderTotal(double price) { return getQuantityOrdered() * price; }
    }
}
