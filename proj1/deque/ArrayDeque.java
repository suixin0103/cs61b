package deque;
import java.util.Iterator;

public class ArrayDeque<Item> implements Deque<Item>, Iterable<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(Item item) {
        items = (Item[]) new Object[8];
        items[3] = item;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque<Item> other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        int current = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[current];
            current = (current + 1) % items.length;
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
        newItems = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int Pos = (nextFirst + 1) % items.length;
            System.out.print(items[Pos] + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        Item removeValue = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if ((size < items.length / 4 && size > 0)) {
            resize((int) (items.length) / 4);
        }
        return removeValue;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        Item valueRemove = items[nextLast];
        items[nextLast] = null;
        size--;
        if ((size < items.length / 4) && size > 0) {
            resize((int) (items.length) / 4);
        }
        return valueRemove;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> ad = (ArrayDeque<?>) o;
        if (ad.size != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (ad.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<Item> {
        int Pos;

        public ArrayDequeIterator() {
            Pos = (nextFirst + 1) % items.length;
        }

        @Override
        public boolean hasNext() {
            return Pos != nextLast;
        }

        @Override
        public Item next() {
            Item returnValue = items[Pos];
            Pos = (Pos + 1) % items.length;
            return returnValue;
        }
    }
}