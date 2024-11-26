package bstmap;


import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.delete;

public class BSTMap<K extends  Comparable<K>, V> implements Map61B<K, V>{
    private BstNode root;
    private V removeValue;
    private class BstNode  {
        K key;
        V value;
        BstNode left, right;
        int size;
        public BstNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("can't search with null key");
        return get(root, key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }
    private V get(BstNode node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }
        else if (cmp > 0) {
            return get(node.right, key);
        }
        return node.value;
    }
    private int size(BstNode node) {
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
        if (node == null) return new BstNode(key, value);
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.value = value;
        }
        else if (cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }
        node.size  = 1 + size(node.left) + size(node.right);
        return node;
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private void DeleteMin() {
        if (size() == 0) return;
        root = DeleteMin(root);
    }
    private BstNode DeleteMin(BstNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = DeleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
    private void DeleteMax() {
        if (size() == 0) return;
        root = DeleteMax(root);
    }
    private BstNode DeleteMax(BstNode node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = DeleteMax(root.right);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("can't delete with a null key");
        removeValue = null;
        root = remove(root, key);
        return removeValue;
    }
    private BstNode remove(BstNode node, K key) {
        if(node == null) return null;
        int cmp = node.key.compareTo(key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        }
        else if (cmp > 0) {
            node.right = remove(node.right, key);
        }
        else {
            removeValue = node.value;
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return  node.right;
            }
            else {
                BstNode minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = remove(node.right, node.key);
            }
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private BstNode findMin(BstNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    @Override
    public V remove(K key, V value) {
        if (size() == 0) throw new NoSuchElementException("Symbol table underflow");
        if(key == null) throw new IllegalArgumentException("can't remove null");
        removeValue = null;
        root = remove(root, key, value);
        return removeValue;

    }
    private BstNode remove(BstNode node, K key, V value) {
        if (node == null) return null;
        int cmp = node.key.compareTo(key);
        if (cmp < 0) {
            node.left = remove(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = remove(node.right, key, value);
        }
        else  if (value != null && value.equals(node.value))
        {
            removeValue = node.value;
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            BstNode minNode = findMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            remove(root.right, minNode.key);
        }
        node.size = 1 +size(node.left) + size(node.right);
        return node;
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
