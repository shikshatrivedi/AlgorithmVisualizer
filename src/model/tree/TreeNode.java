package model.tree;

/**
 * The building block for a Binary Tree.
 * Each node has a value and points to a left and right child.
 */
public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}