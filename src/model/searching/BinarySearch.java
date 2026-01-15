package model.searching;

import model.AlgorithmStep;
import model.SearchingAlgorithm;
import java.util.Arrays;

/**
 * Binary Search implementation.
 * It repeatedly divides the search interval in half.
 */
public class BinarySearch extends SearchingAlgorithm {

    @Override
    public void search(int[] arr, int target) {
        this.target = target;
        this.steps.clear();
        this.resultIndex = -1;

        // Binary search requires a sorted array
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);

        addStep(sortedArr, new int[]{}, "Starting Binary Search (Array must be sorted)", "START");

        int left = 0;
        int right = sortedArr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Highlight the current range and the middle element
            addStep(sortedArr, new int[]{left, mid, right}, "Checking middle index: " + mid, "COMPARE");

            if (sortedArr[mid] == target) {
                this.resultIndex = mid;
                addStep(sortedArr, new int[]{mid}, "Target found at index " + mid, "FINISH");
                return;
            }

            if (sortedArr[mid] < target) {
                addStep(sortedArr, new int[]{mid}, target + " is greater than " + sortedArr[mid] + ". Searching right half.", "HIGHLIGHT");
                left = mid + 1;
            } else {
                addStep(sortedArr, new int[]{mid}, target + " is less than " + sortedArr[mid] + ". Searching left half.", "HIGHLIGHT");
                right = mid - 1;
            }
        }

        addStep(sortedArr, new int[]{}, "Target not found.", "FINISH");
    }
}