package controller;

import model.*;
import model.sorting.*;
import model.searching.*;
import view.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private AnimationManager animationManager;
    private VisualizationPanel vizPanel;
    private InputPanel inputPanel;
    private ControlPanel controlPanel;
    private InfoPanel infoPanel;
    private Timer timer;

    public MainController(AnimationManager am, VisualizationPanel vp, InputPanel ip, ControlPanel cp, InfoPanel infp) {
        this.animationManager = am;
        this.vizPanel = vp;
        this.inputPanel = ip;
        this.controlPanel = cp;
        this.infoPanel = infp;

        initController();
    }

    private void initController() {
        // Play Button logic
        controlPanel.addPlayListener(e -> {
            animationManager.setPlaying(true);
            startTimer();
        });

        // Pause Button logic
        controlPanel.addPauseListener(e -> {
            animationManager.setPlaying(false);
            if (timer != null) timer.stop();
        });

        // Next Button logic
        controlPanel.addNextListener(e -> {
            animationManager.nextStep();
            updateUI();
        });

        // Previous Button logic
        controlPanel.addPrevListener(e -> {
            animationManager.previousStep();
            updateUI();
        });

        // Start/Generate Button logic
        inputPanel.addStartListener(e -> handleStart());
    }

    private void handleStart() {
        String input = inputPanel.getArrayInput();
        String[] items = input.split(",");
        int[] arr = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            arr[i] = Integer.parseInt(items[i].trim());
        }

        String algo = inputPanel.getSelectedAlgorithm();
        
        // Pick the algorithm based on selection
        if (algo.equals("Bubble Sort")) {
            BubbleSort bs = new BubbleSort();
            bs.sort(arr);
            animationManager.setSteps(bs.getSteps());
        } else if (algo.equals("Selection Sort")) {
            SelectionSort ss = new SelectionSort();
            ss.sort(arr);
            animationManager.setSteps(ss.getSteps());
        } // ... (Other algorithms would be added here)

        updateUI();
    }

    private void startTimer() {
        if (timer != null) timer.stop();
        timer = new Timer(controlPanel.getSpeedSlider().getValue(), e -> {
            if (animationManager.isPlaying()) {
                animationManager.nextStep();
                updateUI();
                if (animationManager.getCurrentStepIndex() >= animationManager.getTotalSteps() - 1) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void updateUI() {
        AlgorithmStep step = animationManager.getCurrentStep();
        if (step != null) {
            infoPanel.updateInfo(step.getDescription(), step.getStepNumber(), animationManager.getTotalSteps());
        }
        vizPanel.repaint();
    }
}