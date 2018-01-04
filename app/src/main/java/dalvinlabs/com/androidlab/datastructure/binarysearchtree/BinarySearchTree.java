package dalvinlabs.com.androidlab.datastructure.binarysearchtree;

import android.support.annotation.NonNull;
import android.util.Pair;

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
class BinarySearchTree {

    Node root;
    private int count = 0;

    BinarySearchTree(int data) {
        root = new Node(data);
        count += 1;
    }

    /**
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

    // PUBLIC

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

    /**
        Keep traversing left until encounter null
     */
    Node findMinimum() {
        if (root == null) {
            System.out.println("Tree is empty");
            return null;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private class Pair {
        Node first;
        Node second;

        Pair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Go to the right child of the node as right child is bigger than the node
     * Then keep traversing towards left until leaf is found
     * Leaf found is the successor
     */
    private Pair findSuccessor(@NonNull Node node) {
        if (node.right == null) {
            System.out.println("There's no successor");
            return null;
        }
        Node current = node.right;
        Node parent = node.right;
        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        return new Pair(parent, current);
    }

    /**
        Keep traversing right until encounter null
     */
    Node findMaximum() {
        if (root == null) {
            System.out.println("Tree is empty");
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    /**
        Left Parent Right
     */
    String printInOrder(Node node) {
        String data = "";
        if (node != null) {
            data += printInOrder(node.left);
            data += node.print();
            data += printInOrder(node.right);
        }
        return data;
    }

    /**
     * TODO
     */
    void printPreOrder(Node parent) {

    }

    /**
     * TODO
     */
    void printPostOrder(Node parent) {

    }

    /**
        Breadth first : print one level at a time
    */
    void printBFS() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println("TREE BFS TRAVERSAL\n");
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

    /**
     *
     * Deleting a node consist of first finding a node which needs to be deleted.
     * Following are possible scenarios while deleting a node
     *
     * 1. Node to be deleted is a leaf node (Simply point the parent to null)
     * 2. Node to be deleted has ONE child
     * 3. Node to be deleted has TWO child (Most complex)
     */
    void delete(int data) {
        if (root == null) {
            System.out.println("Tree is empty");
        }
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        while (data != current.data) {
            if (data < current.data) {
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else {
                parent = current;
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                System.out.println("Node is not found");
                return;
            }
        }

        if (current.left == null && current.right == null) {
            // Delete if node to delete is a leaf node
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            // Delete if node to delete has left child
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            // Delete if node to delete has right child
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Pair pair = findSuccessor(current);
            Node successorParent = pair.first;
            Node successor = pair.second;
            if (current.right == successor) {
                // Successor is a right child of node to be deleted
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            } else {
                // Successor is in the left path of node to be deleted
                successorParent.left = successor.right;
                successor.right = current.right;
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
            successor.left = current.left;
        }
    }
}
