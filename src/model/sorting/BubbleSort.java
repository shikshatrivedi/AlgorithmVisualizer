package model.sorting;

import model.AlgorithmStep;
import model.SortingAlgorithm;

/**
 * Bubble Sort implementation.
 * It compares neighbors and "bubbles" the largest number to the end.
 */
public class BubbleSort extends SortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        // Clear any previous steps
        this.steps.clear();

        // 1. Initial snapshot of the unsorted array
        addStep(arr, new int[]{}, "Starting Bubble Sort", "START");

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // 2. Record Comparison Step
                addStep(arr, new int[]{j, j + 1}, "Comparing " + arr[j] + " and " + arr[j+1], "COMPARE");

                if (arr[j] > arr[j + 1]) {
                    // Swap the elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // 3. Record Swap Step
                    addStep(arr, new int[]{j, j + 1}, "Swapping " + arr[j+1] + " and " + arr[j], "SWAP");
                }
            }
            // 4. Mark the end of the array as sorted
            addStep(arr, new int[]{n - 1 - i}, "Element at index " + (n-1-i) + " is sorted", "SORTED");
        }
        
        // 5. Final snapshot
        addStep(arr, new int[]{}, "Sorting Complete", "FINISH");
    }
}