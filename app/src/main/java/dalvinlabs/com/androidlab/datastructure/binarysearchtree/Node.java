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

    String print() {
        //System.out.print(data + " ");
        return data + "";
    }
}
