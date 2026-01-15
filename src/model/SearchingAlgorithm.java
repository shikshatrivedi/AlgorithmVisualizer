package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the blueprint for all Searching algorithms.
 * It tracks the steps and also keeps track of the "target" number we are looking for.
 */
public abstract class SearchingAlgorithm {
    protected List<AlgorithmStep> steps; // The list of snapshots for animation
    protected int target;                // The number we want to find
    protected int resultIndex = -1;      // The position where we found the number (-1 means not found)

    public SearchingAlgorithm() {
        this.steps = new ArrayList<>();
    }

    public List<AlgorithmStep> getSteps() {
        return steps;
    }

    public int getResultIndex() {
        return resultIndex;
    }

    /**
     * Records a snapshot of the search process.
     */
    protected void addStep(int[] array, int[] highlightedIndices, String action, String actionType) {
        steps.add(new AlgorithmStep(array, highlightedIndices, action, actionType, steps.size() + 1));
    }

    /**
     * Every searching algorithm must implement this search method.
     * @param arr The array to search through.
     * @param target The number to look for.
     */
    public abstract void search(int[] arr, int target);
}