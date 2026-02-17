package model.sorting;

import model.AlgorithmStrategy;
import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class QuickSort implements AlgorithmStrategy {
    private List<AlgorithmStep> steps;
    private int stepCount;

    @Override
    public List<AlgorithmStep> generateSteps(int[] array, int target) {
        steps = new ArrayList<>();
        stepCount = 0;
        int[] arr = array.clone();
        
        steps.add(new AlgorithmStep(arr.clone(), null, "Starting Quick Sort", "Start", 0, ++stepCount));
        sort(arr, 0, arr.length - 1);
        steps.add(new AlgorithmStep(arr.clone(), null, "Quick Sort Complete!", "Done", 0, ++stepCount));
        
        return steps;
    }

    private void sort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        // Visualize Pivot Selection
        steps.add(new AlgorithmStep(arr.clone(), new int[]{high}, "Pivot Selected: " + pivot + " (at index " + high + ")", "Partitioning", 1, ++stepCount));

        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // Visualize Comparison
            steps.add(new AlgorithmStep(arr.clone(), new int[]{j, high}, "Comparing " + arr[j] + " vs Pivot " + pivot, "Comparing", 2, ++stepCount));

            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                // Visualize Swap
                steps.add(new AlgorithmStep(arr.clone(), new int[]{i, j}, "Swapped " + arr[i] + " with " + arr[j] + " (smaller than pivot)", "Swapping", 3, ++stepCount));
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Visualize Pivot Placement
        steps.add(new AlgorithmStep(arr.clone(), new int[]{i + 1}, "Pivot " + pivot + " placed at correctly sorted position " + (i+1), "Placed", 4, ++stepCount));

        return i + 1;
    }
}