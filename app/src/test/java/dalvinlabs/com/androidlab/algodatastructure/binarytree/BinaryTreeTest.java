package dalvinlabs.com.androidlab.algodatastructure.binarytree;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

    private BinaryTree binaryTree;

    @Before
    public void before() {
        binaryTree = new BinaryTree();
    }

    @Test
    public void testDataIsAtLeavesOnly() {
        binaryTree.add("A");
        binaryTree.add("B");
        binaryTree.add("C");
        binaryTree.add("D");
        binaryTree.add("E");
        binaryTree.printTree();
        String output = binaryTree.preorder(binaryTree.root);
        Assert.assertEquals("++++ABCDE", output);
    }

    @Test
    public void testCreateBalancedTree() {
        binaryTree.addIntoQueue("A");
        binaryTree.addIntoQueue("B");
        binaryTree.addIntoQueue("C");
        binaryTree.addIntoQueue("D");
        binaryTree.addIntoQueue("E");
        binaryTree.createBalanced();
        binaryTree.printTree();
    }
}
