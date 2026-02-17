package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class MergeSort implements AlgorithmStrategy {
    private List<AlgorithmStep> steps;
    private int stepCount;

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        steps = new ArrayList<>();
        stepCount = 0;
        int[] arr = array.clone();
        steps.add(new AlgorithmStep(arr.clone(), null, "Starting Merge Sort", "Start", 0, ++stepCount));
        
        sort(arr, 0, arr.length - 1);
        
        steps.add(new AlgorithmStep(arr.clone(), null, "Merge Sort Complete", "Done", 0, ++stepCount));
        return steps;
    }

    private void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            
            steps.add(new AlgorithmStep(arr.clone(), new int[]{l, r}, 
                "Dividing range [" + l + ".." + r + "] into [" + l + ".." + m + "] and [" + (m+1) + ".." + r + "]", 
                "Dividing", 1, ++stepCount));

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    private void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        
        // Visualize the range being merged
        steps.add(new AlgorithmStep(arr.clone(), new int[]{l, r}, "Merging sub-arrays range " + l + " to " + r, "Merging", 2, ++stepCount));

        while (i < n1 && j < n2) {
            // Visualize Comparison
            steps.add(new AlgorithmStep(arr.clone(), new int[]{l + i, m + 1 + j}, 
                "Comparing L: " + L[i] + " vs R: " + R[j], "Comparing", 3, ++stepCount));

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            // Visualize Overwrite
            steps.add(new AlgorithmStep(arr.clone(), new int[]{k}, "Placed " + arr[k] + " at index " + k, "Writing", 4, ++stepCount));
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            steps.add(new AlgorithmStep(arr.clone(), new int[]{k}, "Copying remaining from Left: " + L[i], "Copying", 4, ++stepCount));
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            steps.add(new AlgorithmStep(arr.clone(), new int[]{k}, "Copying remaining from Right: " + R[j], "Copying", 4, ++stepCount));
            j++;
            k++;
        }
    }
}