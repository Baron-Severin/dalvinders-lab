package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import junit.framework.Assert;

import org.junit.Test;

public class DelimiterParserTest {

    @Test
    public void testValid() {
        Assert.assertTrue(DelimiterParser.validate("["));
        Assert.assertTrue(DelimiterParser.validate("{"));
        Assert.assertTrue(DelimiterParser.validate("("));
        Assert.assertTrue(DelimiterParser.validate(")"));
        Assert.assertTrue(DelimiterParser.validate("}"));
        Assert.assertTrue(DelimiterParser.validate("]"));
    }

    @Test
    public void testInValid() {
        Assert.assertTrue(DelimiterParser.validate("["));
        Assert.assertTrue(DelimiterParser.validate("{"));
        Assert.assertTrue(DelimiterParser.validate("("));
        Assert.assertTrue(DelimiterParser.validate(")"));
        Assert.assertFalse(DelimiterParser.validate("]"));
    }
}
