package dalvinlabs.com.androidlab.crackingcode;


public class Node {
    int data;
    int dx;

    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
