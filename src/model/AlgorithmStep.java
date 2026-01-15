package model;

import java.util.Arrays;

/**
 * This class represents a single "frame" or "snapshot" of an algorithm.
 * It stores what the data looked like at a specific moment in time.
 */
public class AlgorithmStep {
    private final int[] arraySnapshot;     // A copy of the array at this step
    private final int[] highlightedIndices; // Which numbers are being compared/moved
    private final String action;           // A text description (e.g., "Comparing 5 and 10")
    private final String actionType;       // Type of action (e.g., "COMPARE", "SWAP", "SORTED")
    private final int stepNumber;          // The sequence number of this step

    public AlgorithmStep(int[] array, int[] highlightedIndices, String action, String actionType, int stepNumber) {
        // We create a copy of the array so that future changes to the 
        // original array don't change this "snapshot."
        this.arraySnapshot = Arrays.copyOf(array, array.length);
        this.highlightedIndices = highlightedIndices;
        this.action = action;
        this.actionType = actionType;
        this.stepNumber = stepNumber;
    }

    // Getters: These allow other parts of the program to "read" the data
    public int[] getArraySnapshot() {
        return arraySnapshot;
    }

    public int[] getHighlightedIndices() {
        return highlightedIndices;
    }

    /**
     * Renamed from getAction to getDescription to match MainController.java
     */
    public String getDescription() {
        return action;
    }

    public String getActionType() {
        return actionType;
    }

    public int getStepNumber() {
        return stepNumber;
    }
}