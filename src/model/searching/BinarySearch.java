package model.searching;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class BinarySearch implements AlgorithmStrategy {

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        
        // Binary Search MUST work on a sorted array. 
        // We clone and sort it so the algorithm logic is valid.
        int[] arr = array.clone();
        Arrays.sort(arr); 
        
        // Initial state showing sorted array
        steps.add(new AlgorithmStep(arr.clone(), null));

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Highlight the boundaries (left, right) and the mid point
            // We pass 3 indices to highlight: left, right, and mid
            steps.add(new AlgorithmStep(arr.clone(), new int[]{left, right, mid}));

            if (arr[mid] == target) {
                // Found it! Highlight just the winner.
                steps.add(new AlgorithmStep(arr.clone(), new int[]{mid}));
                return steps;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Not found
        steps.add(new AlgorithmStep(arr.clone(), null));
        return steps;
    }
}