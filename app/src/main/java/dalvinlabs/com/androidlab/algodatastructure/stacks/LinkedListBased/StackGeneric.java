package dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased;

/*
    1. Based upon single linked list
    2. FILO
    3. Push time complexity : 1
    4. Pop time complexity : 1
 */
class StackGeneric<T> {

    private class Node {
        T data;
        Node next;
    }

    private Node start;

    void push(final T data) {
        Node node = new Node();
        node.data = data;
        // New node points to previous top of the stack element
        node.next = start;
        // New node becomes itself top of the stack element
        start = node;
    }

    T pop() {
        T data = null;
        if (start != null) {
            data = start.data;
            // Move the top of the stack to next element
            start = start.next;
        } else {
            System.out.println("Stack is empty");
        }
        return data;
    }

    void print() {
        Node node = start;
        while (node != null) {
            System.out.println(node.data.toString());
            node = node.next;
        }
    }

}
