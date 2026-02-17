package model;

import java.util.Arrays;

/**
 * Immutable snapshot of a single algorithm step for visualization.
 * All array data is defensively cloned on construction and retrieval.
 */
public class AlgorithmStep {
    private final int[] arraySnapshot;
    private final int[] highlightedIndices;
    private final String description;
    private final String status;
    private final int complexityLine;
    private final int stepNumber;

    // --- CONSTRUCTOR 1: Simple (backward-compatible) ---
    public AlgorithmStep(int[] array, int[] highlights) {
        this(array, highlights, "Processing...", "Active", 0, 0);
    }

    // --- CONSTRUCTOR 2: Advanced (full metadata) ---
    public AlgorithmStep(int[] array, int[] highlights, String description, String status, int complexityLine) {
        this(array, highlights, description, status, complexityLine, 0);
    }

    // --- CONSTRUCTOR 3: Complete (with step number) ---
    public AlgorithmStep(int[] array, int[] highlights, String description, String status, int complexityLine, int stepNumber) {
        this.arraySnapshot = array != null ? array.clone() : new int[0];
        this.highlightedIndices = highlights != null ? highlights.clone() : null;
        this.description = description;
        this.status = status;
        this.complexityLine = complexityLine;
        this.stepNumber = stepNumber;
    }

    // --- Immutable Getters (return clones) ---
    public int[] getArraySnapshot() {
        return arraySnapshot.clone();
    }

    public int[] getHighlightedIndices() {
        return highlightedIndices != null ? highlightedIndices.clone() : null;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getComplexityLine() {
        return complexityLine;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    @Override
    public String toString() {
        return "AlgorithmStep{step=" + stepNumber + ", array=" + Arrays.toString(arraySnapshot) +
               ", highlights=" + Arrays.toString(highlightedIndices) +
               ", desc='" + description + "'}";
    }
}