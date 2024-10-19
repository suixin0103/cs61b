package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;
import edu.princeton.cs.algs4.StdRandom;
public class ArrayDequeTest {
    @Test
    public void addLastTest() {
        ArrayDeque<Integer> testArray = new ArrayDeque<>();
        testArray.addLast(1);
        assertEquals("The size should be 1", 1 , testArray.size());
        testArray.addLast(2);
        assertEquals("The size should be 1", 2 , testArray.size());
    }
    @Test
    public void equalTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            ad2.addLast(i);
            ad1.addLast(i);
            Assert.assertTrue(ad2.equals(ad1));
        }
    }
    @Test
    public void equalStringTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad1.addLast("Hello");
        ad2.addLast("Hello");
        ad1.addLast("World");
        ad2.addLast("World");
        Assert.assertTrue(ad1.equals(ad2));
    }
    @Test
    public  void randomTest(){
        int M = 5000;
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for(int i = 0;i < 5000; i++) {
            int randomNum = StdRandom.uniform(1,6);
            if (i == 1) {
                randomNum = StdRandom.uniform(1,100);
                testDeque.addFirst(randomNum);
                assertEquals(randomNum,(int) testDeque.get(0));
            } else if (i == 2) {
                randomNum = StdRandom.uniform(1, 100);
                testDeque.addLast(randomNum);
                assertEquals(randomNum, (int) testDeque.get(testDeque.size() -1 ));
            } else if (i == 3) {
                testDeque.removeFirst();

            } else if (i == 4) {
                testDeque.removeLast();
            } else {
                if (!testDeque.isEmpty()) {
                    randomNum = StdRandom.uniform(0, testDeque.size());
                    assertNotNull(testDeque.get(randomNum));
                }
            }
        }
        testDeque.printDeque();
    }


}
