package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements AlgorithmStrategy {

    public static final String COMPLEXITY = "Time: O(n²) | Space: O(1)";

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int stepCount = 0;
        
        // Working with a copy to avoid modifying the original immediately
        int[] arr = array.clone();
        int n = arr.length;

        // Add initial state
        steps.add(new AlgorithmStep(arr.clone(), null, "Initial array state", "Ready", 0, ++stepCount));

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // 1. Highlight the two bars being compared
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j + 1},
                        "Comparing index " + j + " and " + (j + 1) + " → [" + arr[j] + "] vs [" + arr[j + 1] + "]",
                        "Comparing", 1, ++stepCount));

                if (arr[j] > arr[j + 1]) {
                    // Swap logic
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // 2. Snapshot after swap to show the change
                    steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j + 1},
                            "Swapped " + arr[j] + " and " + arr[j + 1],
                            "Swapped", 2, ++stepCount));
                }
            }
            // Pass completed
            steps.add(new AlgorithmStep(arr.clone(), null,
                    "Pass " + (i + 1) + " completed",
                    "Pass Done", 0, ++stepCount));
        }
        
        // Final state (no highlights)
        steps.add(new AlgorithmStep(arr.clone(), null, "Sorting complete!", "Done", 0, ++stepCount));
        return steps;
    }
}