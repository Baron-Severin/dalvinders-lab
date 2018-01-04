package dalvinlabs.com.androidlab.datastructure.binarysearchtree;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree;

    @Before
    public void createTree() {
        binarySearchTree = new BinarySearchTree(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(7);
        binarySearchTree.insert(6);
        binarySearchTree.insert(8);
        binarySearchTree.insert(9);
        binarySearchTree.insert(1);
        binarySearchTree.insert(0);
        binarySearchTree.insert(4);
        binarySearchTree.insert(2);
        binarySearchTree.printBFS();
    }

    @Test
    public void testInOrder() {
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "0123456789");
    }

    @Test
    public void testSearch() {
        Node node = binarySearchTree.search(9);
        Assert.assertEquals(node.data, 9);
    }

    @Test
    public void testMinimum() {
        Node minimum = binarySearchTree.findMinimum();
        Assert.assertEquals(minimum.data, 0);
    }

    @Test
    public void testMaximum() {
        Node maximum = binarySearchTree.findMaximum();
        Assert.assertEquals(maximum.data, 9);
    }

    @Test
    public void testDeleteWhenNodeHasNoChildren() {
        // Delete a node with no children
        binarySearchTree.delete(2);
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "013456789");
        binarySearchTree.printBFS();
    }

    @Test
    public void testDeleteWhenNodeHasOneChild() {
        // Delete a node with ONE child
        binarySearchTree.delete(8);
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "012345679");
        binarySearchTree.printBFS();
    }

    @Test
    public void testDeleteWhenNodeHasTwoChildren_1() {
        binarySearchTree.delete(7);
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "012345689");
        binarySearchTree.printBFS();
    }

    @Test
    public void testDeleteWhenNodeHasTwoChildren_2() {
        binarySearchTree.delete(5);
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "012346789");
        binarySearchTree.printBFS();
    }

    @Test
    public void testDeleteWhenNodeHasTwoChildren_3() {
        binarySearchTree.delete(3);
        String output = binarySearchTree.printInOrder(binarySearchTree.root);
        Assert.assertEquals(output, "012456789");
        binarySearchTree.printBFS();
    }
}
