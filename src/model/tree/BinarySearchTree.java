package model.tree;

import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

/**
 * A Binary Search Tree that records steps for different types of traversals.
 */
public class BinarySearchTree {
    private TreeNode root;
    private List<AlgorithmStep> steps;

    public BinarySearchTree() {
        this.root = null;
        this.steps = new ArrayList<>();
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertRecursive(root.left, value);
        } else if (value > root.value) {
            root.right = insertRecursive(root.right, value);
        }
        return root;
    }

    public List<AlgorithmStep> getInorderSteps() {
        steps.clear();
        generateInorder(root);
        return steps;
    }

    private void generateInorder(TreeNode node) {
        if (node != null) {
            generateInorder(node.left);
            // Record visiting this node
            steps.add(new AlgorithmStep(new int[]{node.value}, new int[]{0}, "Visiting Node: " + node.value, "TREE_VISIT", steps.size()));
            generateInorder(node.right);
        }
    }

    // Additional traversal methods (Preorder/Postorder) follow the same logic...
    public TreeNode getRoot() {
        return root;
    }
}