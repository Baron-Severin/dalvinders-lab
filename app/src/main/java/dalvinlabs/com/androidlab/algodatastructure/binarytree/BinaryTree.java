package dalvinlabs.com.androidlab.algodatastructure.binarytree;

import java.util.ArrayList;
import java.util.List;

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

    private List<BinaryTree> list = new ArrayList<>();

    /*
        8.1
     */
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
        list.add(binaryTree);
    }

    /*
        8.2
     */
    void createBalanced() {
        if (list.isEmpty()) {
            System.out.println("Tree is empty");
        }
        BinaryTree first;
        BinaryTree second = null;
        BinaryTree balanced;
        Node node;
        List<BinaryTree> localList = new ArrayList<>();
        while (list.size() > 1) {
            localList.addAll(list);
            list.clear();
            while (!localList.isEmpty()) {
                first = localList.remove(0);
                if (!localList.isEmpty()) {
                    second = localList.remove(0);
                    node = new Node("+");
                    if (first != null) {
                        node.left = first.root;
                    }
                    if (second != null) {
                        node.right = second.root;
                    }
                    balanced = new BinaryTree(node);
                } else {
                    balanced = first;
                }
                list.add(balanced);
            }
        }
        root = list.get(0).root;
    }

    /*
        8.3
    */
    Node createTopDown(String[] array, int i) {
        Node parent = new Node(array[i - 1]);
        if (2 * i <= array.length) {
            parent.left = createTopDown(array, 2 * i);
        }
        if (2 * i + 1 <= array.length) {
            parent.right = createTopDown(array, 2 * i + 1);
        }
        root = parent;
        return parent;
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
