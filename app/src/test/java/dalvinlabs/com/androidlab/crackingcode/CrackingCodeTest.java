package dalvinlabs.com.androidlab.crackingcode;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CrackingCodeTest {

    private MinimalTree minimalTree;
    private ListOfDepths listOfDepths;
    private static final int[] input = {1, 2, 3, 4, 5, 6, 7};
    //private static final int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Before
    public void before() {
        minimalTree = new MinimalTree();
        listOfDepths = new ListOfDepths();
    }

    /**
     * 8.2
     */
    @Test
    public void testMinimalTree() {
        minimalTree.create(input);
        minimalTree.print();
    }

    /**
     * 8.3
     */
    @Test
    public void testListOfDepths() {
        minimalTree.create(input);
        minimalTree.print();
        LinkNode[] lists = listOfDepths.createLists(minimalTree);
        Assert.assertNotNull(lists);
        Assert.assertEquals(3, lists.length);
        LinkNode first = lists[0];
        Assert.assertEquals(3, first.data);

        LinkNode second = lists[1];
        Assert.assertEquals(1, second.data);
        Assert.assertEquals(5, second.next.data);

        LinkNode third = lists[2];
        Assert.assertEquals(0, third.data);
        Assert.assertEquals(2, third.next.data);
        Assert.assertEquals(4, third.next.next.data);
        Assert.assertEquals(6, third.next.next.next.data);
    }

}
