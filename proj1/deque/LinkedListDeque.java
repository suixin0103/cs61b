package deque;

import java.util.Arrays;
import java.util.Iterator;
import jh61b.junit.In;
import java.util.LinkedList;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }
    public T getFirst() {
        if (size == 0) {
            return null;
        }
        return sentinel.next.item;
    }

    public T getLast() {
        if (size == 0) {
            return null;
        }
        return sentinel.prev.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> nodeToRemove = sentinel.next;
        sentinel.next = nodeToRemove.next;
        sentinel.next.prev = sentinel;
        size--;
        return nodeToRemove.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> nodeToRemove = sentinel.prev;
        sentinel.prev = nodeToRemove.prev;
        sentinel.prev.next = sentinel;
        size--;
        return nodeToRemove.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getHelper(index, sentinel.next);
    }

    private T getHelper(int index, Node<T> p) {
        if (index == 0) {
            return p.item;
        }
        return getHelper(index - 1, p.next);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(this == o) {
            return true;
        }
        if(o instanceof LinkedListDeque) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if(lld.size != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();

    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> p;
        public void LinkedListDequeIterator() {
            p = sentinel;
        }
        public boolean hasNext() {
            if(p.next == sentinel) {
                return false;
            }
            p = p.next;
            return true;
        }
        public T next() {
            return p.item;
        }
    }
}