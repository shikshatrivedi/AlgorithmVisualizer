package test;

import model.AlgorithmStep;
import model.sorting.*;
import model.searching.*;
import java.util.Arrays;
import java.util.List;

public class AlgorithmTest {
    
    public static void main(String[] args) {
        System.out.println("====== STARTING SYSTEM SELF-TEST ======");
        
        boolean allPassed = true;
        allPassed &= testSorting("Bubble Sort", new BubbleSort());
        allPassed &= testSorting("Selection Sort", new SelectionSort());
        allPassed &= testSorting("Insertion Sort", new InsertionSort());
        allPassed &= testSorting("Merge Sort", new MergeSort());
        allPassed &= testSorting("Quick Sort", new QuickSort());
        
        allPassed &= testBinarySearch();

        System.out.println("=======================================");
        if (allPassed) {
            System.out.println("✅ ALL SYSTEMS OPERATIONAL. READY FOR LAUNCH.");
        } else {
            System.out.println("❌ SYSTEM FAILURE DETECTED.");
        }
    }

    private static boolean testSorting(String name, model.AlgorithmStrategy algo) {
        int[] input = {50, 10, 40, 20, 30};
        int[] expected = {10, 20, 30, 40, 50};
        
        List<AlgorithmStep> steps = algo.generateSteps(input.clone(), 0);
        
        // The last step should have the sorted array
        int[] result = steps.get(steps.size() - 1).getArraySnapshot();
        
        if (Arrays.equals(result, expected)) {
            System.out.println("✅ " + name + ": PASSED");
            return true;
        } else {
            System.out.println("❌ " + name + ": FAILED");
            System.out.println("   Expected: " + Arrays.toString(expected));
            System.out.println("   Got:      " + Arrays.toString(result));
            return false;
        }
    }

    private static boolean testBinarySearch() {
        // Binary Search expects sorted data
        int[] input = {10, 20, 30, 40, 50}; 
        int target = 40;
        
        BinarySearch algo = new BinarySearch();
        List<AlgorithmStep> steps = algo.generateSteps(input, target);
        
        // Check if the last step description indicates success
        AlgorithmStep lastStep = steps.get(steps.size() - 1);
        boolean found = lastStep.getDescription().contains("Found");
        
        if (found) {
            System.out.println("✅ Binary Search: PASSED");
            return true;
        } else {
            System.out.println("❌ Binary Search: FAILED (Target 40 not found)");
            return false;
        }
    }
}