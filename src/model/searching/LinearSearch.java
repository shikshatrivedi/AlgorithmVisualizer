package model.searching;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class LinearSearch implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int[] arr = array.clone(); // Keep original array structure

        steps.add(new AlgorithmStep(arr.clone(), null));

        for (int i = 0; i < arr.length; i++) {
            // Highlight the current index being checked
            steps.add(new AlgorithmStep(arr.clone(), new int[]{i}));

            if (arr[i] == target) {
                // FOUND! You might want to handle a "success" color in the View later,
                // but for now, we just end the animation here.
                steps.add(new AlgorithmStep(arr.clone(), new int[]{i})); 
                return steps;
            }
        }

        // Target not found step
        steps.add(new AlgorithmStep(arr.clone(), null));
        return steps;
    }
}