package dalvinlabs.com.androidlab.crackingcode;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CrackingCodeTest {

    private MinimalTree minimalTree;
    private ListOfDepths listOfDepths;
    private CheckBalanced checkBalanced;
    private static final int[] input = {1, 2, 3, 4, 5, 6, 7};
    //private static final int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Before
    public void before() {
        minimalTree = new MinimalTree();
        listOfDepths = new ListOfDepths();
        checkBalanced = new CheckBalanced();
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
    public void testListOfDepths_1() {
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

    /**
     * 8.3
     */
    @Test
    public void testListOfDepths_2() {
        minimalTree.create(input);
        minimalTree.print();
        System.out.println("\nLists:");
        List<LinkedList<Node>> lists = listOfDepths.createListsUsingPreOrder(minimalTree);
        String actual = "";
        for (LinkedList<Node> eachLinkedList : lists) {
            System.out.println(eachLinkedList.toString());
        }
    }

    /**
     * 8.4
     */
    @Test
    public void testCheckBalanced() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.left.left.left = new Node(1);
        root.left.left.left.right = new Node(2);


        TreeUtils.print(root);
        boolean value = checkBalanced.isBalanced(root);

        System.out.println("Is Balanced = " + value);

        Assert.assertEquals(false, value);
    }
}
