package dalvinlabs.com.androidlab.datastructure.binarysearchtree;


import org.junit.Test;

import io.reactivex.Observable;

public class BinarySearchTreeTest {

    @Test
    public void test() {
        BinarySearchTree binarySearchTree = new BinarySearchTree(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(8);
        binarySearchTree.insert(6);
        binarySearchTree.insert(9);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(2);
        binarySearchTree.printBFS();
        System.out.println("\nInOrder, also Ascending order");
        binarySearchTree.printInOrder(binarySearchTree.root);
        Node node = binarySearchTree.search(9);
        System.out.println();
        if (node != null) {
            System.out.println("Key 9 is Found in tree" );
        } else {
            System.out.println("Key 9 is NOT Found in tree" );
        }
        Node minimum = binarySearchTree.findMinimum();
        if (minimum != null) {
            System.out.println("\nMinimum in tree = " + minimum.data);
        }
        Node maximum = binarySearchTree.findMaximum();
        if (maximum != null) {
            System.out.println("\nMaximum in tree = " + maximum.data);
        }
    }
}
