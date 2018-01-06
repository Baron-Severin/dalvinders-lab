package dalvinlabs.com.androidlab.algodatastructure.binarytree;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class FullBinaryTreeTest {

    private FullBinaryTree fullBinaryTree;

    @Before
    public void before() {
        fullBinaryTree = new FullBinaryTree();
    }

    @Test
    public void testDataIsAtLeavesOnly() {
        fullBinaryTree.add("A");
        fullBinaryTree.add("B");
        fullBinaryTree.add("C");
        fullBinaryTree.add("D");
        fullBinaryTree.add("E");
        fullBinaryTree.printTree();
        String output = fullBinaryTree.preorder(fullBinaryTree.root);
        Assert.assertEquals("++++ABCDE", output);
    }
}
