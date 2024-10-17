package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>  {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        comparator =c;
    }
    public T max() {
        if(isEmpty()) {
            return null;
        }
        T maxValue = get(0);
        for(int i = 0; i < size(); i++) {
            T current = get(i);
            if( comparator.compare(maxValue, current) < 0) {
                maxValue = current;
            }
        }
        return maxValue;
    }

    public T max(Comparator<T> c) {
        if(isEmpty()) {
            return null;
        }
        T maxValue = get(0);
        for(int i = 0; i < size(); i++) {
            T currentValue = get(i);
            if(c.compare(currentValue, maxValue) > 0) {
                maxValue = currentValue;
            }
        }
        return maxValue;
    }

}
