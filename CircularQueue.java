class CircularQueue {
    private int[] arr;
    private int front, rear, size;

    public CircularQueue(int capacity) {
        arr = new int[capacity];
        front = -1;
        rear = -1;
        size = capacity;
    }

    public boolean isFull() {
        return (front == (rear + 1) % size);
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % size;
        arr[rear] = value;
        if (front == -1)
            front = rear;
        System.out.println("Inserted: " + value);
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        int removed = arr[front];
        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % size;
        System.out.println("Removed: " + removed);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        int i = front;
        System.out.print("Queue contents: ");
        while (true) {
            System.out.print(arr[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    // Main
    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();

        cq.dequeue();
        cq.display();

        cq.enqueue(40);
        cq.enqueue(50);
        cq.enqueue(60);
        cq.display();
    }
}
