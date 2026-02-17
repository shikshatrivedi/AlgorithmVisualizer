package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements AlgorithmStrategy {
    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int stepCount = 0;
        int[] arr = array.clone();
        int n = arr.length;

        steps.add(new AlgorithmStep(arr.clone(), null, "Initial array state", "Ready", 0, ++stepCount));

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Visualize selecting the key
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, 
                "Pass " + i + ": Selected key " + key + " to insert", "Processing", 1, ++stepCount));

            while (j >= 0 && arr[j] > key) {
                // Visualize comparison
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j, j+1}, 
                    "Comparing " + arr[j] + " > " + key + " → Shift required", "Comparing", 2, ++stepCount));
                
                arr[j + 1] = arr[j]; // Shift
                j = j - 1;
                
                // Visualize shift
                steps.add(new AlgorithmStep(arr.clone(), new int[]{j+1}, 
                    "shifted " + arr[j+1] + " to the right", "Shifting", 3, ++stepCount));
            }
            arr[j + 1] = key;
            
            // Visualize placement
            steps.add(new AlgorithmStep(arr.clone(), new int[]{j+1}, 
                "Inserted " + key + " at index " + (j+1), "Inserted", 4, ++stepCount));
        }

        steps.add(new AlgorithmStep(arr.clone(), null, "Sorting complete!!", "Done", 0, ++stepCount));
        return steps;
    }
}