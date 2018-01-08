package dalvinlabs.com.androidlab.algodatastructure.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.QueueGeneric;
import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

/**
 * Full BT = Each node has 0 or 2 children
 * Complete BT = Each not has 2 children except last level and last level children as left as possible
 */
public class BinaryTree {

    private static class Node {

        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
        }
    }

    Node root;

    BinaryTree() {}

    public BinaryTree(Node root) {
        this.root = root;
    }

    private QueueGeneric<BinaryTree> queue = new QueueGeneric<>();

    void add(String data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node parent = new Node("+");
        parent.left = root;
        parent.right = new Node(data);
        root = parent;
    }

    void addIntoQueue(String data) {
        BinaryTree binaryTree = new BinaryTree(new Node(data));
        queue.enqueue(binaryTree);
    }

    void createBalanced() {
        BinaryTree first;
        BinaryTree second;
        BinaryTree balanced = null;
        Node node;
        while (!queue.isEmpty()) {
            first = queue.dequeue();
            second = queue.dequeue();
            node = new Node("+");
            node.left = first.root;
            if (second != null) {
                node.right = second.root;
            }
            balanced = new BinaryTree(node);
            queue.enqueue(balanced);
        }
        if (balanced != null) {
            root = balanced.root;
        }
    }

    String preorder(Node node) {
        String data = "";
        data = data + node.data;
        if (node.left != null) {
            data = data + preorder(node.left);
        }
        if (node.right != null) {
            data = data + preorder(node.right);
        }
        return data;
    }

    void printTree() {
        if (root == null) {
            System.out.println("Empty Tree");
            return;
        }
        StackGeneric<Node> globalStack = new StackGeneric<>();
        globalStack.push(root);
        int mBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("..............................................");
        while (isRowEmpty == false) {
            StackGeneric<Node> localStack = new StackGeneric<>();
            isRowEmpty = true;

            for (int i = 0; i < mBlanks; i++) {
                System.out.print(" ");
            }

            while (globalStack.isEmpty() == false) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null && temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("..");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < mBlanks * 2 - 2; i++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            mBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("..............................................");
    }
}
