package dalvinlabs.com.androidlab.algodatastructure.binarytree;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import dalvinlabs.com.androidlab.algodatastructure.stacks.use.InfixToPostfix;

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

    @Test
    public void testCreateTopDown() {
        String[] input = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        binaryTree.createTopDown(input, 1);
        binaryTree.printTree();
    }

    @Test
    public void testCreateFromPostfix() throws Exception {
        String postfix = "12345+^*+";
        binaryTree.createFromPostfix(postfix);
        String postOrder = binaryTree.postOrder(binaryTree.root);
        String inOrder = binaryTree.inOrderWithBrackets(binaryTree.root);
        System.out.println("postfix   = " + postfix);
        System.out.println("postOrder = " + postOrder);
        System.out.println("inOrder   = " + inOrder);
        binaryTree.printTree();
        Assert.assertEquals(postfix, postOrder);
        String postfixFromInOrder = InfixToPostfix.convert(inOrder);
        Assert.assertEquals(InfixToPostfix.calculate(postfix), InfixToPostfix.calculate(postfixFromInOrder));
    }
}
