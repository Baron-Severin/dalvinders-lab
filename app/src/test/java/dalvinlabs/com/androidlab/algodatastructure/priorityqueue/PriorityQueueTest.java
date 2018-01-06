package dalvinlabs.com.androidlab.algodatastructure.priorityqueue;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

    private PriorityQueue priorityQueue = new PriorityQueue(5);

    @Before
    public void before() {

    }

    @Test
    public void test() {
        priorityQueue.insert(10);
        priorityQueue.insert(20);
        priorityQueue.insert(30);
        priorityQueue.insert(25);
        priorityQueue.insert(5);
        priorityQueue.print();
        String actual = "";
        for (int i = 0; i < 5; i++) {
            actual = actual + priorityQueue.remove() + ", ";
        }
        Assert.assertEquals(actual, "5, 10, 20, 25, 30, ");
    }

    @Test
    public void testAlternative() {
        priorityQueue.insertAlternate(10);
        priorityQueue.insertAlternate(20);
        priorityQueue.insertAlternate(30);
        priorityQueue.insertAlternate(25);
        priorityQueue.insertAlternate(5);
        priorityQueue.print();
        String actual = "";
        for (int i = 0; i < 5; i++) {
            actual = actual + priorityQueue.removeAlternate() + ", ";
        }
        System.out.println("\nRemoved order");
        System.out.println(actual);
        Assert.assertEquals(actual, "5, 10, 20, 25, 30, ");
    }
}
