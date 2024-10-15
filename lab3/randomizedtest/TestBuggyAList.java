package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> testListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer>      testListBuggy      = new BuggyAList<>();

        testListNoResizing.addLast(4);
        testListNoResizing.addLast(5);
        testListNoResizing.addLast((6));

        testListBuggy.addLast(4);
        testListBuggy.addLast(5);
        testListBuggy.addLast(6);

        assertEquals(testListNoResizing.size(), testListBuggy.size());
        assertEquals(testListNoResizing.removeLast(), testListBuggy.removeLast());
        Assert.assertEquals(testListNoResizing.removeLast(), testListBuggy.removeLast());


        //copy from website
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> TestBuggy2 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if(L.size() == 0) {
                    continue;
                }
                int lastItem = L.getLast();
                System.out.println("lastItem: " + lastItem);

            } else if (operationNumber == 3) {
                if(L.size() == 0) {
                    continue;
                }
                int removeItem = L.removeLast();
                System.out.println("removeItem: " + removeItem);

            }
        }

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                TestBuggy2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = TestBuggy2.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if(TestBuggy2.size() == 0) {
                    continue;
                }
                int lastItem = TestBuggy2.getLast();
                System.out.println("lastItem: " + lastItem);

            } else if (operationNumber == 3) {
                if(TestBuggy2.size() == 0) {
                    continue;
                }
                int removeItem = TestBuggy2.removeLast();
                System.out.println("removeItem: " + removeItem);

            }
        }
    }
}
