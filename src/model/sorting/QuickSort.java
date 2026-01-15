package model.sorting;

import model.AlgorithmStep;
import model.SortingAlgorithm;

/**
 * Quick Sort implementation.
 * It picks an element as 'pivot' and partitions the given array around the pivot.
 */
public class QuickSort extends SortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        this.steps.clear();
        addStep(arr, new int[]{}, "Starting Quick Sort", "START");
        quickSort(arr, 0, arr.length - 1);
        addStep(arr, new int[]{}, "Sorting Complete", "FINISH");
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // partitionIndex is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        addStep(arr, new int[]{high}, "Chosen pivot: " + pivot, "HIGHLIGHT");
        
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            addStep(arr, new int[]{j, high}, "Comparing " + arr[j] + " with pivot " + pivot, "COMPARE");
            
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                addStep(arr, new int[]{i, j}, "Swapping elements to move smaller to the left", "SWAP");
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        addStep(arr, new int[]{i + 1, high}, "Moving pivot to its correct sorted position", "SWAP");
        addStep(arr, new int[]{i + 1}, "Pivot is now at its final position", "SORTED");

        return i + 1;
    }
}