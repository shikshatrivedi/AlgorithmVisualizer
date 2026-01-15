package model.searching;

import model.AlgorithmStep;
import model.SearchingAlgorithm;

/**
 * Linear Search implementation.
 * It checks every single element in the array one by one until the target is found.
 */
public class LinearSearch extends SearchingAlgorithm {

    @Override
    public void search(int[] arr, int target) {
        this.target = target;
        this.steps.clear();
        this.resultIndex = -1;

        addStep(arr, new int[]{}, "Starting Linear Search for: " + target, "START");

        for (int i = 0; i < arr.length; i++) {
            // Record that we are checking this specific index
            addStep(arr, new int[]{i}, "Checking index " + i + " (Value: " + arr[i] + ")", "COMPARE");

            if (arr[i] == target) {
                this.resultIndex = i;
                addStep(arr, new int[]{i}, "Target " + target + " found at index " + i + "!", "FINISH");
                return;
            }
        }

        addStep(arr, new int[]{}, "Target " + target + " not found in the array.", "FINISH");
    }
}