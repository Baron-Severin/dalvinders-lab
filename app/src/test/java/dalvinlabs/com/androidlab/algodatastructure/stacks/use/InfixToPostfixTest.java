package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import junit.framework.Assert;

import org.junit.Test;

public class InfixToPostfixTest {

    @Test
    public void test1() throws Exception {
        String infix = "1+2-3";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("12+3-", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(0, output);
    }

    @Test
    public void test2() throws Exception {
        String infix = "1*2+3";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("12*3+", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(5, output);
    }

    @Test
    public void test3() throws Exception {
        String infix = "1+2*3*4";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("123*4*+", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(25, output);
    }

    @Test
    public void test4() throws Exception {
        String infix = "1+2*3^4+5";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("1234^*+5+", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(168, output);
    }

    @Test
    public void test5() throws Exception {
        String infix = "1+2*3^(4+5)";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("12345+^*+", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(39367, output);
    }

    @Test
    public void test6() throws Exception {
        String infix = "1+2*3^(4+5)+(2-4)";
        String postfix = InfixToPostfix.convert(infix);
        System.out.println("infix = " + infix);
        System.out.println("postfix = " + postfix);
        Assert.assertEquals("12345+^*+24-+", postfix);
        int output = InfixToPostfix.calculate(postfix);
        System.out.println("output = " + output);
        Assert.assertEquals(39365, output);
    }
}
