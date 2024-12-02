package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {



    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor;
    private int initialSize;
    private int size;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {

        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor =maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */

    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = createTable(initialSize);

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("can't find a null key");
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("can't find a null key");
        int index = getBucketIndex(key);
        if(buckets[index] == null) return null;
        else {
            for(Node node : buckets[index]) {
                if (node.key.equals(key)) return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("can't put a null key");
        else {
            int index = getBucketIndex(key);
            for (Node node : buckets[index]) {
                if (node.key == key) {
                    node.value = value;
                    return;
                }
            }
            buckets[index].add(new Node(key, value));
            size++;
            }

        }


    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(Collection<Node> bu : buckets) {
            for (Node node : bu) {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {

        if (key == null) throw new IllegalArgumentException("can't remove a null key");
        int index = getBucketIndex(key);
        for (Node node : buckets[index]) {
            V removeValue;
            if (node.key.equals(key)) {
                removeValue = node.value;
                buckets[index].remove(node);
                size--;
                return removeValue;
            }
        }
        return null;
        }


    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("can't remove a null key");
        int index = getBucketIndex(key);
        for (Node node : buckets[index]) {
            V removeValue;
            if (node.key.equals(key) && node.value.equals(value)) {
                removeValue = node.value;
                buckets[index].remove(node);
                size--;
                return removeValue;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }
    private class MyHashMapIterator implements Iterator<K>{
        private int bucketIndex = 0;
        private Iterator<Node> bucketIterator = null;
        public MyHashMapIterator() {
            moveToNextBucket();
        }
        private void moveToNextBucket() {
            while (bucketIndex < buckets.length ) {
                if (buckets[bucketIndex] != null && !buckets[bucketIndex].isEmpty()) {
                    bucketIterator = buckets[bucketIndex].iterator();
                    return;
                }
                bucketIndex++;
            }
            bucketIterator = null;
        }
        @Override
        public K next() {
            if (hasNext()) {
                return bucketIterator.next().key;
            }
            return null;
        }
        @Override
        public boolean hasNext() {
            if (bucketIterator != null && bucketIterator.hasNext()) return true;
            moveToNextBucket();
            return bucketIterator != null && bucketIterator.hasNext();
        }
    }
    private int getBucketIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

}
