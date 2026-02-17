package model.searching;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class BinarySearch implements AlgorithmStrategy {

    public static final String COMPLEXITY = "Time: O(log n) | Space: O(1)";

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        List<AlgorithmStep> steps = new ArrayList<>();
        int stepCount = 0;
        
        // Binary Search MUST work on a sorted array. 
        // We clone and sort it so the algorithm logic is valid.
        int[] arr = array.clone();
        Arrays.sort(arr); 
        
        // Initial state showing sorted array
        steps.add(new AlgorithmStep(arr.clone(), null, "Sorted array loaded for Binary Search", "Ready", 0, ++stepCount));

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Highlight the boundaries (left, right) and the mid point
            steps.add(new AlgorithmStep(arr.clone(), new int[]{left, right, mid},
                    "Checking mid=" + mid + " → value " + arr[mid] + " | range [" + left + ".." + right + "]",
                    "Searching", 0, ++stepCount));

            if (arr[mid] == target) {
                // Found it!
                steps.add(new AlgorithmStep(arr.clone(), new int[]{mid},
                        "Found target " + target + " at index " + mid,
                        "Found", 0, ++stepCount));
                return steps;
            }

            if (arr[mid] < target) {
                steps.add(new AlgorithmStep(arr.clone(), new int[]{mid},
                        arr[mid] + " < " + target + " → searching right half",
                        "Narrowing", 0, ++stepCount));
                left = mid + 1;
            } else {
                steps.add(new AlgorithmStep(arr.clone(), new int[]{mid},
                        arr[mid] + " > " + target + " → searching left half",
                        "Narrowing", 0, ++stepCount));
                right = mid - 1;
            }
        }

        // Not found
        steps.add(new AlgorithmStep(arr.clone(), null,
                "Target " + target + " not found in array",
                "Not Found", 0, ++stepCount));
        return steps;
    }
}