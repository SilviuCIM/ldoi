public class CircularQueue {
    private int[] queue;
    private int capacity;
    private int head;
    private int tail;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.head = -1;
        this.tail = -1;
    }
    public void ENQUEUE(int x) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        if (isEmpty()) {
            head = 0;
            tail = 0;
        } else {
            tail = (tail + 1) % capacity;
        }

        queue[tail] = x;
    }

    public int DEQUEUE() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1; // Return a sentinel value indicating an empty queue.
        }

        int removedValue = queue[head];

        if (head == tail) {
            // The queue is now empty after dequeueing the last element.
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity;
        }

        return removedValue;
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        int current = head;
        while (true) {
            System.out.print(queue[current] + " ");
            if (current == tail) {
                break; // Reached the end of the queue
            }
            current = (current + 1) % capacity;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.ENQUEUE(1);
        queue.ENQUEUE(2);
        queue.ENQUEUE(3);

        queue.displayQueue();
       // queue.DEQUEUE();
        queue.ENQUEUE(4);
        queue.ENQUEUE(5);
        queue.DEQUEUE();
        queue.ENQUEUE(6);
        queue.displayQueue();
        queue.ENQUEUE(7);
        queue.displayQueue();
        System.out.println(queue.head);
    }
}
