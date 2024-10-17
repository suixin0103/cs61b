package deque;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.Comparator;
import org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(7);
        mad.addLast(1);
        mad.addLast(2);
        int maxValue = mad.max();
        Assert.assertEquals("Answer should be 7",7,maxValue);
    }

    @Test
    public void maxStringTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringComparator());
        mad.addLast("Hi");
        mad.addLast("hi");
        mad.addLast("ai");
        String maxString = mad.max();
        Assert.assertEquals("Answer should be hi", "hi", maxString);
    }

    @Test
    public void maxWithComparatorTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringComparator());

        mad.addLast("Java is good!");
        mad.addLast("java is good");

        Assert.assertEquals("java is good", mad.max());
        Assert.assertEquals("Java is good!", mad.max(new StringLengthComparator()));
    }
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return (i1 - i2);
        }
    }
    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int l1 = s1.length();
            int l2 = s2.length();
            for(int i = 0; i < Math.min(l1, l2); i++) {
                int c1 = s1.charAt(i);
                int c2 = s2.charAt(i);

                if(c1 != c2) {
                    return (c1 -c2);
                }
            }
            if(l1 !=l2) {
                return (l1 -l2);
            }
            return 0;
        }
    }
    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return (s1.length() - s2.length());
        }
    }
}
