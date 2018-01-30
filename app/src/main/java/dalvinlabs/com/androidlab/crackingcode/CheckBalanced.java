package dalvinlabs.com.androidlab.crackingcode;

/**
 * 4.4
 */
public class CheckBalanced {

    public boolean isBalanced(Node node) {
        int lh = getHeight(node.left, 0);
        int rh = getHeight(node.right, 0);
        System.out.println("LH = " + lh);
        System.out.println("RH = " + rh);
        int diff = Math.abs(lh - rh);
        return diff <= 1;
    }

    private int getHeight(Node node, int height) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node == null) return height;
        leftHeight = getHeight(node.left, height + 1);
        rightHeight = getHeight(node.right, height + 1);
        height =  Math.max(leftHeight, rightHeight);
        return height;
    }

}
