package dalvinlabs.com.androidlab.datastructure.binarysearchtree;

class Node {

    int data;
    String prefix = "";
    String suffix = "";
    int tabs;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }

    /*
        Left Parent Right
     */
    void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(data);
        if (right != null) {
            right.printInOrder();
        }
    }
}
