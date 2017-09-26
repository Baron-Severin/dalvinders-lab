package dalvinlabs.com.androidlab.algo.stacks.LinkedListBased;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackGenericTest {

    private StackGeneric<String> stackGeneric;

    @Before
    public void init() {
        stackGeneric = new StackGeneric<>();
    }

    @Test
    public void success() {
        stackGeneric.push("abc");
        stackGeneric.push("def");
        stackGeneric.push("ghi");
        stackGeneric.print();

        String data = stackGeneric.pop();
        System.out.println("Pop = " + data);
        Assert.assertTrue(data.equals("ghi"));
        stackGeneric.print();

        data = stackGeneric.pop();
        System.out.println("Pop = " + data);
        Assert.assertTrue(data.equals("def"));
        stackGeneric.print();

        data = stackGeneric.pop();
        System.out.println("Pop = " + data);
        Assert.assertTrue(data.equals("abc"));
        stackGeneric.print();

        data = stackGeneric.pop();
        System.out.println("Pop = " + data);
        stackGeneric.print();
    }

    @Test
    public void underflow() {
        String data = stackGeneric.pop();
        Assert.assertNull(data);
    }
}
