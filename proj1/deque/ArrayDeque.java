    package deque;

    public class ArrayDeque<Item> {
        private Item[] items;
        private int size;
        private int nextFirst;
        private int nextLast;
        public  ArrayDeque() {
            items = (Item[]) new Object[8];
            size = 0;
            nextFirst = 3;
            nextLast = 4;
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
            items[nextFirst] = item;
            /*Item[] newItems = (Item[]) new Object[items.length];
            newItems[0] = item;
            System.arraycopy(items,0,newItems,1,size);
            items = newItems;
            /
             */
            nextFirst = (nextFirst - 1 + items.length) % items.length;
            size++;
        }

        public void addLast(Item item) {
            if(size == items.length){
                resize(size);
            }
            items[nextLast] = item;
            nextLast = (nextLast + 1) % items.length;
            size++;
        }

        public void resize(int capacity) {
            Item[] newItems = (Item[]) new Object[capacity * 2];
            int current = nextFirst + 1;
            for(int i = 0; i < size ;i++){
                newItems[i] = items[current];
                current = (current + 1) % items.length;
            }
            items = newItems;
            nextFirst = capacity - 1 ;
            nextLast = size;

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

            nextFirst = (nextFirst + 1)%items.length;
            Item removeValue = items[nextFirst];
            items[nextFirst] = null;
            size--;
            if((size < items.length/4 && size > 0)) {
                resize((int)(items.length)/4);
            }
            return removeValue;
        }

        public Item removeLast() {
            if (isEmpty()) {
                return null;
            }
            nextLast =(nextLast -1 + items.length) % items.length;
            Item valueRemove = items[nextLast];
            items[nextLast] = null;

            size--;
            if((size < items.length/4)&& size > 0) {
                resize((int)(items.length)/4);
            }
            return valueRemove;
        }

        public Item get(int index) {
            if (index < 0 || index >= size) {
                return null;
            }
            int actualIndex = (nextFirst + 1 + index) % items.length;
            return items[actualIndex];
        }



    }
