package view;

import model.AlgorithmStep;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the playback of algorithm steps.
 * Like a video player, it keeps track of the "current frame."
 */
public class AnimationManager {
    private List<AlgorithmStep> steps;
    private int currentStepIndex;
    private boolean isPlaying;
    private int speed; // Delay in milliseconds

    public AnimationManager() {
        this.steps = new ArrayList<>();
        this.currentStepIndex = -1;
        this.isPlaying = false;
        this.speed = 500; // Default speed: half a second per step
    }

    public void setSteps(List<AlgorithmStep> steps) {
        this.steps = steps;
        this.currentStepIndex = 0;
    }

    public AlgorithmStep getCurrentStep() {
        if (currentStepIndex >= 0 && currentStepIndex < steps.size()) {
            return steps.get(currentStepIndex);
        }
        return null;
    }

    public void nextStep() {
        if (currentStepIndex < steps.size() - 1) {
            currentStepIndex++;
        }
    }

    public void previousStep() {
        if (currentStepIndex > 0) {
            currentStepIndex--;
        }
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
    
    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public int getTotalSteps() {
        return steps.size();
    }
}