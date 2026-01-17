package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        
        // Working with a copy to avoid modifying the original immediately
        int[] arr = array.clone();
        int n = arr.length;

        // Add initial state
        steps.add(new AlgorithmStep(arr.clone(), null));

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // 1. Highlight the two bars being compared (Red/Pink in your new UI)
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j + 1}));

                if (arr[j] > arr[j + 1]) {
                    // Swap logic
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // 2. Snapshot after swap to show the change
                    steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j + 1}));
                }
            }
        }
        
        // Final state (no highlights)
        steps.add(new AlgorithmStep(arr.clone(), null));
        return steps;
    }
}