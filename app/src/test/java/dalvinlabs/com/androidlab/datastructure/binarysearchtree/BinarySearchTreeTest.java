package dalvinlabs.com.androidlab.datastructure.binarysearchtree;


import org.junit.Test;

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
    }
}
