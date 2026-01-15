package model.sorting;

import model.AlgorithmStep;
import model.SortingAlgorithm;

/**
 * Insertion Sort implementation.
 * It builds the sorted array one item at a time by "inserting" 
 * the current value into its correct position.
 */
public class InsertionSort extends SortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        this.steps.clear();

        addStep(arr, new int[]{}, "Starting Insertion Sort", "START");

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            addStep(arr, new int[]{i}, "Picking key: " + key, "HIGHLIGHT");

            // Move elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                addStep(arr, new int[]{j, j + 1}, "Comparing " + arr[j] + " > " + key, "COMPARE");
                
                arr[j + 1] = arr[j];
                
                addStep(arr, new int[]{j, j + 1}, "Shifting " + arr[j] + " to the right", "SWAP");
                j = j - 1;
            }
            arr[j + 1] = key;
            
            addStep(arr, new int[]{j + 1}, "Inserted " + key + " into position", "SORTED");
        }
        
        addStep(arr, new int[]{}, "Sorting Complete", "FINISH");
    }
}