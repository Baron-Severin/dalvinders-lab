package dalvinlabs.com.androidlab.algo.stacks.stackbasedonarray;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FixedCapacityStackOfStringsV1Test {

    private FixedCapacityStackOfStringsV1 mStack;
    private String mData[] = {"abc", "def", "ghi", "jkl", "mno"};

    @Before
    public void init() {
        mStack = new FixedCapacityStackOfStringsV1(5);
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
    public void testOverflow() throws Exception {
        for (String eachString : mData) {
            System.out.println("Pushing item = " + eachString);
            mStack.push(eachString);
            mStack.print();
            System.out.println("# # # # #");
        }
        try {
            System.out.println("Pushing Overflow item");
            mStack.push("Overflow item");
            Assert.fail("Expects stack overflow");
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            Assert.assertTrue(e.getMessage().equalsIgnoreCase("Stack overflow"));
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

}
