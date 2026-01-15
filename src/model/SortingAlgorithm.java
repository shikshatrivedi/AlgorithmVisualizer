package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a "Master Template." 
 * It says: "I don't know HOW you will sort yet (Bubble sort? Quick sort?), 
 * but I know you WILL need a list of steps to show the user."
 */
public abstract class SortingAlgorithm {
    
    // This is a folder-like list that holds all our snapshots (steps)
    protected List<AlgorithmStep> steps;

    // This sets up the list when we start
    public SortingAlgorithm() {
        this.steps = new ArrayList<>();
    }

    // This lets the user interface "get" the steps to show the animation
    public List<AlgorithmStep> getSteps() {
        return steps;
    }

    // This is like pressing the "Shutter" button on a camera to save a step
    protected void addStep(int[] array, int[] highlightedIndices, String action, String actionType) {
        // We create a new Step and add it to our list
        steps.add(new AlgorithmStep(array, highlightedIndices, action, actionType, steps.size() + 1));
    }

    // This is a "Requirement." 
    // Any real algorithm (like BubbleSort) MUST fill in the blanks for this.
    public abstract void sort(int[] arr);
}