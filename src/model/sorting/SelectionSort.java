package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        int n = arr.length;

        steps.add(new AlgorithmStep(arr.clone(), null));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                // Highlight the current minimum candidate vs the current item being checked
                steps.add(new AlgorithmStep(arr.clone(), new int[]{minIndex, j}));
                
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            // Snapshot after swap
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i, minIndex}));
        }

        steps.add(new AlgorithmStep(arr.clone(), null));
        return steps;
    }
}