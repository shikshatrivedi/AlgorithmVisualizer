package model.searching;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class LinearSearch implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int stepCount = 0;
        int[] arr = array.clone(); // Keep original array structure

        steps.add(new AlgorithmStep(arr.clone(), null, "Starting Linear Search for target: " + target, "Start", 0, ++stepCount));

        for (int i = 0; i < arr.length; i++) {
            // Highlight the current index being checked
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, 
                "Checking index " + i + ": value " + arr[i], "Checking", 0, ++stepCount));

            if (arr[i] == target) {
                // FOUND!
                steps.add(new AlgorithmStep(arr.clone(), new int[]{i}, 
                    "Found target " + target + " at index " + i, "Found", 0, ++stepCount)); 
                return steps;
            }
        }

        // Target not found step
        steps.add(new AlgorithmStep(arr.clone(), null, "Target " + target + " not found.", "Not Found", 0, ++stepCount));
        return steps;
    }
}