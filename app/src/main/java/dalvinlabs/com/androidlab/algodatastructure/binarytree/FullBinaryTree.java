package dalvinlabs.com.androidlab.algodatastructure.binarytree;

import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

/**
 * Each node has 0 or 2 children
 */
public class FullBinaryTree {

    private static class Node {

        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
        }
    }

    Node root;

    public FullBinaryTree() {
    }

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
