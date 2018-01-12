package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import junit.framework.Assert;

import org.junit.Test;

public class ReverseWordTest {

    @Test
    public void test() {
        String input = "ABCDE";
        System.out.println("Input = " + input);
        String output = ReverseWord.run(input);
        System.out.println("Output = " + output);
        Assert.assertEquals("EDCBA", output);
    }
}
