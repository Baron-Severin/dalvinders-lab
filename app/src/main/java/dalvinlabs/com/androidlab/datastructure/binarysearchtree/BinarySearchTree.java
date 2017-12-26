package dalvinlabs.com.androidlab.datastructure.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Binary tree where left child < root and right child > root, Duplicates are not allowed.
 * Search time complexity = Log(N)
 * Insert = Search the appropriate spot + insert => Log(N) + 1 => Log(N)
 * Delete = Search the appropriate spot + delete => Log(N) + 1 => Log(N)
 *
 * Hence a well structured balanced tree provides Log(N) for search/insert/delete operations.
 */
public class BinarySearchTree {

    private Node root;
    private int count = 0;

    BinarySearchTree(int data) {
        root = new Node(data);
        count += 1;
    }

    Node search(int data) {
        if (root == null) {
            System.out.println("Tree is empty");
            return null;
        }
        Node current = root;
        while (current != null) {
            if (data < current.data) {
                // go towards left
                current = current.left;
            } else if (data > current.data) {
                // go towards right
                current = current.right;
            } else {
                // data found
                return current;
            }
        }
        // data not found
        return null;
    }

    void insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            count ++;
            return;
        }
        Node current = root;
        Node parent = null;
        // Need to find the appropriate place for insertion
        while (true) {
            parent = current;
            if (data < current.data) {
                // go towards left
                current = current.left;
                if (current == null) {
                    parent.left = node;
                    count ++;
                    return;
                }
            } else {
                // go towards right
                current = current.right;
                if (current == null) {
                    parent.right = node;
                    count ++;
                    return;
                }
            }
        }
    }

    void printInOrder() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.printInOrder();
    }

    /*
        Breadth first : print one level at a time
    */
    void printBFS() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        int height = logBase2(count);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(new Node(-1));
        root.tabs = 2 * height;
        Node current;
        int alreadyPrintedTabs = 0;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.data == -1) {
                System.out.println();
                alreadyPrintedTabs = 0;
                if (!queue.isEmpty()) {
                    queue.add(new Node(-1));
                }
                continue;
            }
            printTabs(current.tabs - alreadyPrintedTabs);
            alreadyPrintedTabs = current.tabs;
            System.out.print(current.prefix);
            System.out.print(current.data);
            System.out.print(current.suffix);
            if (current.left != null) {
                queue.add(current.left);
                current.left.tabs = current.tabs - 2;
                current.left.suffix = "/";
            }
            if (current.right != null) {
                queue.add(current.right);
                current.right.tabs = current.tabs + 2;
                current.right.prefix = "\\";
            }
        }
    }

    /*
        Not accurate, but enough to print tree structure
     */
    private int logBase2(int dividend) {
        int log = 0;
        int remainder = 0;
        while (dividend >= 2) {
            dividend = dividend / 2;
            remainder = dividend % 2;
            log += 1;
        }
        if (remainder >= 1) {
            log += 1;
        }
        return log;
    }

    private void printTabs(long count) {
        for (int i = 0; i < count; i++) {
            System.out.print("\t");
        }
    }

    void delete(int data) {

    }
}
