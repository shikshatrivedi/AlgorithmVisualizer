package model.sorting;

import model.AlgorithmStep;
import model.SortingAlgorithm;

/**
 * Selection Sort implementation.
 * It finds the smallest number in the unsorted part and swaps it to the front.
 */
public class SelectionSort extends SortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        this.steps.clear();

        addStep(arr, new int[]{}, "Starting Selection Sort", "START");

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            // Record that we are looking for the minimum starting at i
            addStep(arr, new int[]{i}, "Looking for minimum starting at index " + i, "COMPARE");

            for (int j = i + 1; j < n; j++) {
                // Record the comparison
                addStep(arr, new int[]{minIdx, j}, "Comparing current min with index " + j, "COMPARE");
                
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                    addStep(arr, new int[]{minIdx}, "New minimum found at index " + minIdx, "HIGHLIGHT");
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;

            addStep(arr, new int[]{i, minIdx}, "Swapping " + arr[minIdx] + " with " + arr[i], "SWAP");
            addStep(arr, new int[]{i}, "Index " + i + " is now sorted", "SORTED");
        }
        
        addStep(arr, new int[]{}, "Sorting Complete", "FINISH");
    }
}