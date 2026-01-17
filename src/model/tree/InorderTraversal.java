package model.tree;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class InorderTraversal implements AlgorithmStrategy {
    private List<AlgorithmStep> steps;

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        steps = new ArrayList<>();
        // Start Step
        steps.add(new AlgorithmStep(array.clone(), null, "Tree Structure Loaded", "Ready", 0));
        
        inorder(array, 0);
        
        steps.add(new AlgorithmStep(array.clone(), null, "Traversal Complete", "Done", 0));
        return steps;
    }

    private void inorder(int[] arr, int index) {
        if (index >= arr.length) return;

        // 1. Visit Left Child (2 * index + 1)
        inorder(arr, 2 * index + 1);

        // 2. Visit Root (Current Node)
        // Highlight the current node we are "visiting"
        steps.add(new AlgorithmStep(arr.clone(), new int[]{index}, "Visiting Node: " + arr[index], "Visiting", 0));

        // 3. Visit Right Child (2 * index + 2)
        inorder(arr, 2 * index + 2);
    }
}