package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements AlgorithmStrategy {
    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone();
        int n = arr.length;

        steps.add(new AlgorithmStep(arr.clone(), null)); // Start

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Visualize selecting the key
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, "Selected key: " + key, "Processing", 0));

            while (j >= 0 && arr[j] > key) {
                // Visualize comparison
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j+1}, "Comparing " + arr[j] + " > " + key, "Comparing", 0));
                
                arr[j + 1] = arr[j]; // Shift
                j = j - 1;
                
                // Visualize shift
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j+1}, "Shifting " + arr[j+1], "Shifting", 0));
            }
            arr[j + 1] = key;
            
            // Visualize placement
            steps.add(new AlgorithmStep(arr.clone(), new int[]{j+1}, "Inserted " + key, "Inserted", 0));
        }

        steps.add(new AlgorithmStep(arr.clone(), null, "Sorted!", "Done", 0));
        return steps;
    }
}