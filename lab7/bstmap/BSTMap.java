package bstmap;


import java.nio.file.Path;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import static java.nio.file.Files.delete;

public class BSTMap<K extends  Comparable<K>, V> implements Map61B<K, V>{
    private BstNode root;


    private class BstNode  {
        K key;
        V value;
        BstNode left, right;
        int size;
        public BstNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 0;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }
    private boolean containsKey(BstNode Node, K key) {
        if (Node == null) return false;
        if (Node.key.equals(key)) return true;
        return containsKey(Node.left, key) || containsKey(Node.right, key);
    }
    @Override
    public V get(K key) {
        return get(root, key);
    }
    private V get(BstNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        }
         else if (cmp < 0) {
            return get(node.left, key); // 在左子树中继续查找
        } else {
            return get(node.right, key); // 在右子树中继续查找
        }
    }

    public int size(BstNode node) {
        if (node == null) return 0;
        return node.size;
    }
    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw  new IllegalArgumentException("can't put a null key");
        root = put(root, key, value);
    }
    private BstNode put(BstNode node, K key, V value) {
        if (node == null) {
            return new BstNode(key, value);
        }
        int cmp = key.compareTo((K) node.key);
        if (cmp == 0) {
            node.value = value;
        }
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = put(node.right, key, value);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public void DeleteMin() {
        if (size() == 0) return;
        root = DeleteMin(root);
    }
    private BstNode DeleteMin(BstNode node) {
        if (node.left == null) {
            return node.right;
        }
        root.left = DeleteMin(node.left);
        node.size = 1 + size(root.left) + size(root.right);
        return node;
    }
    public void DeleteMax() {
        if (size() == 0) return;
        root = DeleteMax(root);
    }
    private BstNode DeleteMax(BstNode node) {
        if (node.right == null) {
            return node.left;
        }
        root.right = DeleteMax(root.right);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(BstNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
    }
}
