import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<Item>();
        node.item = item;
        node.next = first;
        node.previous = null;
        if (isEmpty()) {
            last = node;
        }
        else {
            first.previous = node;
        }
        first = node;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<Item>();
        node.item = item;
        node.next = null;
        node.previous = last;
        if (isEmpty()) {
            first = node;
        }
        else {
            last.next = node;
        }
        last = node;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.previous = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        size--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        DequeIterator(Node<Item> start) {
            current = start;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        deque.addFirst("middle");
        deque.addFirst("first");
        deque.addLast("last");
        for (String item : deque) {
            System.out.println(item);
        }
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());
    }
}
