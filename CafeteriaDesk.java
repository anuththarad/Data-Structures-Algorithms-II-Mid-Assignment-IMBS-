public class CafeteriaDesk {

    // Pending Orders Queue
    private String[] queue;
    private int head;
    private int tail;
    private int size;
    private static final int MAX = 8;

    // Served Orders History
    private Node servedHead;

    // Constructor
    public CafeteriaDesk() {
        queue = new String[MAX];
        head = 0;
        tail = 0;
        size = 0;
        servedHead = null;
    }

    // Submit a New Food Order
    public boolean placeOrder(String orderId) {
        if (isQueueFull()) {
            // Queue is full
            return false;
        }
        queue[tail] = orderId;
        tail = (tail + 1) % MAX;
        size++;
        return true;
    }

    // Serve the Next Food Order
    public String serveNextOrder() {
        if (isQueueEmpty()) {
            // No pending orders
            return null;
        }

        String servedOrder = queue[head];
        head = (head + 1) % MAX;
        size--;

        // Add to served orders list
        Node newNode = new Node();
        newNode.value = servedOrder;
        newNode.next = servedHead;
        servedHead = newNode;

        return servedOrder;
    }

    // Get Recently Served Orders
    public String[] getServedOrderHistory(int N) {
        if (N <= 0) {
            return new String[0];
        }

        String[] temp = new String[N];
        Node current = servedHead;
        int count = 0;

        while (current != null && count < N) {
            temp[count] = current.value;
            current = current.next;
            count++;
        }

        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    // Utility Methods
    public boolean isQueueEmpty() {
        return size == 0;
    }

    public boolean isQueueFull() {
        return size == MAX;
    }

    public int pendingSize() {
        return size;
    }

    // Node Class
    private class Node {
        String value;
        Node next;
    }

    // Main Method
    public static void main(String[] args) {
        CafeteriaDesk desk = new CafeteriaDesk();

        // Placing some orders
        desk.placeOrder("O101");
        desk.placeOrder("O102");
        desk.placeOrder("O103");

        // Serving two orders
        System.out.println("Served Order: " + desk.serveNextOrder()); // O101
        System.out.println("Served Order: " + desk.serveNextOrder()); // O102

        // Get last 5 served orders
        String[] history = desk.getServedOrderHistory(5);
        System.out.println("Recently Served Orders:");
        for (String order : history) {
            System.out.println(order);
        }
    }
}
