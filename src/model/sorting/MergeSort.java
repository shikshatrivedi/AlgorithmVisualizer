package model.sorting;

import model.AlgorithmStep;
import model.SortingAlgorithm;

/**
 * Merge Sort implementation.
 * A Divide and Conquer algorithm that divides the array into halves,
 * sorts them, and then merges them back together.
 */
public class MergeSort extends SortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        this.steps.clear();
        addStep(arr, new int[]{}, "Starting Merge Sort", "START");
        mergeSort(arr, 0, arr.length - 1);
        addStep(arr, new int[]{}, "Sorting Complete", "FINISH");
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Divide: record the split
            addStep(arr, new int[]{left, right}, "Splitting array from index " + left + " to " + right, "HIGHLIGHT");

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Conquer: merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            addStep(arr, new int[]{left + i, mid + 1 + j}, "Comparing elements from sub-arrays", "COMPARE");
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            addStep(arr, new int[]{k}, "Merging back into main array", "SWAP");
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            addStep(arr, new int[]{k}, "Merging remaining elements", "SWAP");
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            addStep(arr, new int[]{k}, "Merging remaining elements", "SWAP");
            j++;
            k++;
        }
    }
}