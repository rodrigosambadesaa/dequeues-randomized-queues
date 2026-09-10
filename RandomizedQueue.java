import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size);
        Item item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniformInt(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] order;
        private int current;

        RandomizedQueueIterator() {
            order = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                order[i] = items[i];
            }
            StdRandom.shuffle(order);
            current = 0;
        }

        public boolean hasNext() {
            return current < order.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return order[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue.sample());
        for (String item : queue) {
            System.out.println(item);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
