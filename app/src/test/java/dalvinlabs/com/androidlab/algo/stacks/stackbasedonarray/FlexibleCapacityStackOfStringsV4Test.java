package dalvinlabs.com.androidlab.algo.stacks.stackbasedonarray;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FlexibleCapacityStackOfStringsV4Test {

    private FlexibleCapacityStackOfStringsV4 mStack;
    private String mData[] = {"abc", "def", "ghi", "jkl", "mno"};

    @Before
    public void init() {
        mStack = new FlexibleCapacityStackOfStringsV4();
    }

    @Test
    public void testSuccess() throws Exception {
        for (String eachString : mData) {
            System.out.println("Pushing item = " + eachString);
            mStack.push(eachString);
            mStack.print();
            System.out.println("# # # # #");
        }
        String popItem;
        for (int i = mData.length - 1; i >= 0; i--) {
            popItem = mStack.pop();
            System.out.println("Popped item = " + popItem);
            Assert.assertTrue(popItem.equalsIgnoreCase(mData[i]));
            mStack.print();
            System.out.println("# # # # #");
        }
    }

    @Test
    public void testUnderFlow() throws Exception {
        try {
            mStack.print();
            System.out.println("Popping from empty stack");
            mStack.pop();
            Assert.fail("Expects stack underflow");
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            Assert.assertTrue(e.getMessage().equalsIgnoreCase("Stack underflow"));
        }
    }

    @Test
    public void testResizeAndShrink() throws Exception {
        for (int i = 0; i < 4; i++) {
            System.out.println("Pushing item = " + mData[i]);
            mStack.push(mData[i]);
            mStack.print();
            System.out.println("# # # # #");
        }
        System.out.println("Pushing item = " + mData[4]);
        mStack.push(mData[4]);
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 8); // Pushing 5th item doubles stack size
        System.out.println("# # # # #");
        System.out.println("Popped item = " + mStack.pop());
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 8); // Popping 5th item halves the stack size
        System.out.println("# # # # #");
        mStack.push(mData[4]);
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 8); // Pushing 5th item doubles stack size
        System.out.println("# # # # #");
        System.out.println("Popped item = " + mStack.pop());
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 8); // Popping 5th item halves the stack size
        System.out.println("# # # # #");
        System.out.println("Popped item = " + mStack.pop());
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 8); // Popping 4th item halves the stack size
        System.out.println("# # # # #");
        System.out.println("Popped item = " + mStack.pop());
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 4); // Popping 3rd item halves the stack size
        System.out.println("# # # # #");
        mStack.push(mData[2]);
        mStack.print();
        Assert.assertTrue(mStack.capacity() == 4); // Pushing 3rd item won't double the size
        System.out.println("# # # # #");
    }
}
