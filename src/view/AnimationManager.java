package view;

import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

public class AnimationManager {
    private List<AlgorithmStep> steps;
    private int currentStepIndex = 0;

    public AnimationManager() {
        this.steps = new ArrayList<>();
    }

    public void setSteps(List<AlgorithmStep> steps) {
        this.steps = steps;
        this.currentStepIndex = 0;
    }

    public AlgorithmStep getCurrentStep() {
        if (steps == null || steps.isEmpty()) return null;
        // Safety check to prevent crashing if index goes out of bounds
        if (currentStepIndex >= steps.size()) currentStepIndex = steps.size() - 1;
        return steps.get(currentStepIndex);
    }

    // --- New Methods for the Controller ---

    // Returns true if we successfully moved forward, false if finished
    public boolean nextStep() {
        if (currentStepIndex < steps.size() - 1) {
            currentStepIndex++;
            return true;
        }
        return false;
    }

    // Returns true if we successfully moved backward
    public boolean prevStep() {
        if (currentStepIndex > 0) {
            currentStepIndex--;
            return true;
        }
        return false;
    }

    public int getCurrentIndex() {
        return currentStepIndex;
    }

    public int getTotalSteps() {
        return steps == null ? 0 : steps.size();
    }

    /** Reset the animation to the first step. */
    public void resetToStart() {
        this.currentStepIndex = 0;
    }
}