package deque;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    public  ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }
    public ArrayDeque(ArrayDeque<Item> other) {
        this();
        for(int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    public void addFirst(Item item) {
        if(size == items.length){
            resize(size);
        }
        Item[] newItems = (Item[]) new Object[items.length];
        newItems[0] = item;
        System.arraycopy(newItems,1,items,0,size);
        items = newItems;
        size++;
        newItems = null;
    }

    public void addLast(Item item) {
        if(size == items.length){
            resize(size);
        }
        items[size] = item;
        size++;
    }

    public void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
        newItems = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        for(int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public Item removeFirst() {
        if(isEmpty()) {
            return null;
        }
        Item removeValue = items[0];
        Item[] newItems = (Item[]) new Object[items.length];
        System.arraycopy(items,1,items,0,size - 1);
        size--;
        return removeValue;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item valueRemove = items[size - 1];
        items[size - 1] = null;
        size--;
        return valueRemove;
    }

    public Item get(int index) {
        return items[index];
    }


}
