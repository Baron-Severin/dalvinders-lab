package dalvinlabs.com.androidlab.crackingcode;

import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

/**
 * 4.3
 * TODO: Solution using pre-order traversal
 * TODO: Understand solution of book.
 */
public class ListOfDepths {

    public LinkNode[] createLists(MinimalTree tree) {
        StackGeneric<Node> currentStack = new StackGeneric<>();
        currentStack.push(tree.getRoot());
        StackGeneric<Node> nextStack = new StackGeneric<>();
        int heightOfTree = tree.heightOfTree(tree.sizeOfTree(tree.getRoot()));
        LinkNode[] lists = new LinkNode[heightOfTree + 1];
        LinkNode linkStart = null;
        LinkNode linkEnd = null;
        int counter = 0;
        while (true) {
            while (!currentStack.isEmpty()) {
                Node node = currentStack.pop();
                if (linkStart == null) {
                    linkStart = new LinkNode(node.data);
                    linkEnd = linkStart;
                } else {
                    LinkNode linkNode = new LinkNode(node.data);
                    linkEnd.next = linkNode;
                    linkEnd = linkNode;
                }
                if (node.left != null) {
                    nextStack.push(node.left);
                }
                if (node.right != null) {
                    nextStack.push(node.right);
                }
            }
            lists[counter++] = linkStart;
            linkStart = null;
            linkEnd = null;
            if (nextStack.isEmpty()) {
                return lists;
            }
            while (!nextStack.isEmpty()) {
                currentStack.push(nextStack.pop());
            }
        }
    }
}
