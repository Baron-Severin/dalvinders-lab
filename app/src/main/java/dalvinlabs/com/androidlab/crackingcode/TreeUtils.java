package dalvinlabs.com.androidlab.crackingcode;


import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

public class TreeUtils {

    private static int sizeOfTree(Node node) {
        int size = 0;
        if (node != null) {
            size += sizeOfTree(node.left);
            size++;
            size += sizeOfTree(node.right);
        }
        return size;
    }

    private static int heightOfTree(int size) {
        int counter = 0;
        while ((size + 1) > Math.pow(2, counter)) {
            counter ++;
        }
        return counter - 1;
    }

    public static void print(Node root) {
        StackGeneric<Node> presentStack = new StackGeneric<>();
        StackGeneric<Node> nextStack = new StackGeneric<>();
        presentStack.push(root);
        Node node;

        int sizeOfTree = sizeOfTree(root);
        int heighOfTree = heightOfTree(sizeOfTree);
        int maxPossibleLeaves = (int) Math.pow(2, heighOfTree);

        System.out.println("Size of tree = " + sizeOfTree);
        System.out.println("Height of tree = " + heighOfTree);
        System.out.println("Max possible leaves = " + maxPossibleLeaves);

        int dx = 2 * maxPossibleLeaves;
        root.dx = dx;
        while (true) {
            while (!presentStack.isEmpty()) {
                node = presentStack.pop();
                for (int i = 0; i < node.dx; i++) {
                    System.out.print(" ");
                }
                System.out.print(node.data);
                for (int i = 0; i < node.dx - 1; i++) {
                    System.out.print(" ");
                }
                dx = node.dx;
                if (node.left != null) {
                    node.left.dx = dx/2;
                    nextStack.push(node.left);
                }
                if (node.right != null) {
                    node.right.dx = dx/2;
                    nextStack.push(node.right);
                }
            }
            if (nextStack.isEmpty()) {
                System.out.println();
                return;
            }
            System.out.println();
            while (!nextStack.isEmpty()) {
                presentStack.push(nextStack.pop());
            }
        }
    }
}
