package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int stepCount = 0;
        int[] arr = array.clone();
        int n = arr.length;

        steps.add(new AlgorithmStep(arr.clone(), null, "Initial array state", "Ready", 0, ++stepCount));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            // Visualize starting a new pass
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, 
                "Pass " + (i + 1) + ": Finding minimum starting from index " + i, "Searching Min", 1, ++stepCount));

            for (int j = i + 1; j < n; j++) {
                // Highlight the current minimum candidate vs the current item being checked
                steps.add(new AlgorithmStep(arr.clone(), new int[]{minIndex, j},
                    "Comparing current min [" + arr[minIndex] + "] with [" + arr[j] + "]", 
                    "Comparing", 1, ++stepCount));
                
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    steps.add(new AlgorithmStep(arr.clone(), new int[]{minIndex}, 
                        "New minimum found: " + arr[minIndex], "New Min", 1, ++stepCount));
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;

                // Snapshot after swap
                steps.add(new AlgorithmStep(arr.clone(), new int[]{i, minIndex},
                    "Swapped minimum element " + temp + " with " + arr[minIndex], "Swapped", 2, ++stepCount));
            } else {
                 steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, 
                    "Element " + arr[i] + " is already in correct position", "No Swap", 2, ++stepCount));
            }
        }

        steps.add(new AlgorithmStep(arr.clone(), null, "Sorting complete!", "Done", 0, ++stepCount));
        return steps;
    }
}