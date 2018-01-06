package dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueGenericTest {

    private QueueGeneric<Integer> queueGeneric;

    @Before
    public void init() {
        queueGeneric = new QueueGeneric<>();
    }

    @Test
    public void success() {
        queueGeneric.enqueue(1);
        queueGeneric.enqueue(2);
        queueGeneric.enqueue(3);
        queueGeneric.enqueue(4);
        queueGeneric.print();

        Integer data = queueGeneric.dequeue();
        System.out.println();
        System.out.println("Dequeue = " + data);
        Assert.assertTrue(data == 1);
        queueGeneric.print();

        data = queueGeneric.dequeue();
        System.out.println();
        System.out.println("Dequeue = " + data);
        Assert.assertTrue(data == 2);
        queueGeneric.print();

        data = queueGeneric.dequeue();
        System.out.println();
        System.out.println("Dequeue = " + data);
        Assert.assertTrue(data == 3);
        queueGeneric.print();

        data = queueGeneric.dequeue();
        System.out.println();
        System.out.println("Dequeue = " + data);
        Assert.assertTrue(data == 4);
        queueGeneric.print();

        data = queueGeneric.dequeue();
        System.out.println();
        System.out.println("Dequeue = " + data);
        queueGeneric.print();
    }

    @Test
    public void underflow() {
        Integer data = queueGeneric.dequeue();
        Assert.assertNull(data);
    }

}
