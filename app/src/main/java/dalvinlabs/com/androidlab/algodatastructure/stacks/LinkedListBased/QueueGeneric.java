package dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased;

import io.reactivex.annotations.NonNull;

/*
    1. Based on single linked list
    2. FIFO
    3. Enqueue time complexity : N
    4. Dequeue time complexity : 1
 */
public class QueueGeneric<T> {

    private class Node {
        T data;
        Node next;
    }

    private Node start;

    private Node getLastNode() {
        Node node = start;
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }

    public void enqueue(final T data) {
        Node node = new Node();
        node.data = data;
        if (start == null) {
            // Queue is empty at present
            start = node;
        } else {
            // Queue is not empty
            getLastNode().next = node;
        }
    }

    public T dequeue() {
        T data = null;
        if (start != null) {
            data = start.data;
            // Point the start to next item
            start = start.next;
        } else {
            System.out.println("Queue is empty");
        }
        return data;
    }

    public void print() {
        Node node = start;
        System.out.print("Queue <--> ");
        while (node != null) {
            System.out.print(node.data.toString() + " ");
            node = node.next;
        }
    }

    public boolean isEmpty() {
        return start == null;
    }

}
