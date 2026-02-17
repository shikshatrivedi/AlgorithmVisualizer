package controller;

import model.AlgorithmStep;
import model.AlgorithmStrategy;
// Sorting Algorithms
import model.sorting.BubbleSort;
import model.sorting.SelectionSort;
import model.sorting.InsertionSort;
import model.sorting.MergeSort;
import model.sorting.QuickSort;
// Searching Algorithms
import model.searching.LinearSearch;
import model.searching.BinarySearch;
// Tree Algorithms
import model.tree.InorderTraversal;
import view.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.List;

public class MainController {
    private AnimationManager animationManager;
    private VisualizationPanel vizPanel;
    private InputPanel inputPanel;
    private ControlPanel controlPanel;
    private InfoPanel infoPanel;
    private HeaderPanel headerPanel;
    private Timer timer;

    // Track current algorithm info for InfoPanel
    private String currentAlgoName = "";
    private String currentComplexity = "";

    public MainController(AnimationManager am, VisualizationPanel vp, InputPanel ip, ControlPanel cp, InfoPanel info, HeaderPanel hp) {
        this.animationManager = am;
        this.vizPanel = vp;
        this.inputPanel = ip;
        this.controlPanel = cp;
        this.infoPanel = info;
        this.headerPanel = hp;

        // 1. Initialize the Timer (speed slider logic refined)
        // Default delay 500ms (based on slider middle)
        timer = new Timer(500, e -> stepForward());

        // 2. Connect the "Generate" Button
        inputPanel.addGenerateListener(e -> handleGenerate());

        // 3. Connect Playback Controls
        controlPanel.addPlayListener(e -> play());
        controlPanel.addPauseListener(e -> pause());
        controlPanel.addNextListener(e -> stepForward());
        controlPanel.addPrevListener(e -> stepBackward());
        controlPanel.addResetListener(e -> handleReset());
        controlPanel.addRestartListener(e -> handleRestart());

        // 5. Connect Speed Slider
        // Mapping: Value 0..990
        // Delay = 1000 - value. Min delay 10ms.
        controlPanel.addSpeedListener(e -> {
            int val = controlPanel.getSpeed();
            int newDelay = 1000 - val;
            if (newDelay < 10) newDelay = 10; 
            timer.setDelay(newDelay);
        });

        // 6. Auto-disable target input based on algorithm selection
        inputPanel.addAlgoChangeListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selected = (String) e.getItem();
                boolean isSearch = selected.contains("Search");
                inputPanel.setTargetEnabled(isSearch);
            }
        });

        // Initialize target state based on default selection
        String defaultAlgo = inputPanel.getSelectedAlgorithm();
        inputPanel.setTargetEnabled(defaultAlgo.contains("Search"));
    }

    // --- Action Handlers ---

    // NEW: Public method to attach keyboard listeners to the Frame
    public void setupKeyboardControls(JFrame frame) {
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case java.awt.event.KeyEvent.VK_SPACE:
                        if (timer.isRunning()) pause(); else play();
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        stepForward();
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        stepBackward();
                        break;
                    case java.awt.event.KeyEvent.VK_R:
                        handleReset();
                        break;
                }
            }
        });
    }

    private void handleGenerate() {
        try {
            // Get inputs from the UI
            String arrayStr = inputPanel.getArrayInput();
            String targetStr = inputPanel.getTargetInput();
            String algoType = inputPanel.getSelectedAlgorithm();

            // Update Header
            headerPanel.setAlgorithmName(algoType);

            // Parse inputs
            int[] data = parseArray(arrayStr);
            if (data.length == 0) throw new IllegalArgumentException("Array cannot be empty.");
            
            int target = 0;
            if (!targetStr.trim().isEmpty()) {
                try {
                    target = Integer.parseInt(targetStr.trim());
                } catch (NumberFormatException ex) {
                    // Ignore target error if not searching, or handle gracefully
                    if (algoType.contains("Search")) {
                        throw new IllegalArgumentException("Target must be a valid number.");
                    }
                }
            }

            AlgorithmStrategy algorithm;
            
            // IMPORTANT: Reset Tree Mode by default
            vizPanel.setTreeMode(false);

            // Switch to select the correct logic
            switch (algoType) {
                case "Bubble Sort":    algorithm = new BubbleSort(); break;
                case "Selection Sort": algorithm = new SelectionSort(); break;
                case "Insertion Sort": algorithm = new InsertionSort(); break;
                case "Merge Sort":     algorithm = new MergeSort(); break;
                case "Quick Sort":     algorithm = new QuickSort(); break;
                case "Linear Search":  algorithm = new LinearSearch(); break;
                
                case "Binary Search":  
                    // AUTO-FIX: Binary Search requires sorted data
                    Arrays.sort(data);
                    algorithm = new BinarySearch();
                    JOptionPane.showMessageDialog(null,
                        "Binary Search requires sorted input.\nArray auto-sorted.",
                        "Auto-Sort Applied", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // --- TREE MODE LOGIC ---
                case "Array Tree Traversal": 
                    algorithm = new InorderTraversal(); 
                    vizPanel.setTreeMode(true);
                    break;
                    
                default: algorithm = new BubbleSort(); break;
            }

            // Generate the animation steps
            List<AlgorithmStep> steps = algorithm.generateSteps(data, target);
            animationManager.setSteps(steps);
            
            // Store current algorithm context
            currentAlgoName = algoType;
            currentComplexity = getComplexity(algoType);

            // Reset UI with full info (step 1-based)
            vizPanel.repaint();
            // Start at Step 1 (Index 0)
            infoPanel.updateInfo("Loaded: " + algoType, 1, steps.size(), currentAlgoName, currentComplexity);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Invalid number format.\nPlease enter integers separated by comma.");
        } catch (IllegalArgumentException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleReset() {
        timer.stop();
        animationManager.resetToStart();
        vizPanel.repaint();
        infoPanel.updateInfo("Reset — Ready.", 1, animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
    }

    private void handleRestart() {
        handleReset();
        play();
    }

    private int[] parseArray(String input) {
        if (input == null || input.trim().isEmpty()) return new int[0];
        
        String[] parts = input.split(",");
        // Filter out empty strings caused by trailing commas
        List<String> validParts = new java.util.ArrayList<>();
        for (String p : parts) {
            if (!p.trim().isEmpty()) validParts.add(p.trim());
        }
        
        int[] arr = new int[validParts.size()];
        for (int i = 0; i < validParts.size(); i++) {
            arr[i] = Integer.parseInt(validParts.get(i));
        }
        return arr;
    }

    // Helper method for complexity
    private String getComplexity(String algo) {
        switch (algo) {
            case "Bubble Sort":    return "Time: O(n²)  |  Space: O(1)";
            case "Selection Sort": return "Time: O(n²)  |  Space: O(1)";
            case "Insertion Sort": return "Time: O(n²)  |  Space: O(1)";
            case "Merge Sort":     return "Time: O(n log n)  |  Space: O(n)";
            case "Quick Sort":     return "Time: O(n log n)  |  Space: O(log n)";
            case "Linear Search":  return "Time: O(n)  |  Space: O(1)";
            case "Binary Search":  return "Time: O(log n)  |  Space: O(1)";
            case "Array Tree Traversal": return "Time: O(n)  |  Space: O(h)";
            default: return "Unknown";
        }
    }

    // --- Playback Logic ---

    private void play() {
        timer.start();
        int displayStep = animationManager.getCurrentIndex() + 1;
        infoPanel.updateInfo("Playing...", displayStep, animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
    }

    private void pause() {
        timer.stop();
        int displayStep = animationManager.getCurrentIndex() + 1;
        infoPanel.updateInfo("Paused.", displayStep, animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
    }

    private void stepForward() {
        if (animationManager.nextStep()) {
            vizPanel.repaint();
            AlgorithmStep step = animationManager.getCurrentStep();
            
            // Use the advanced description if available
            String desc = (step.getDescription() != null) ? step.getDescription() : "Step Forward";
            int displayStep = animationManager.getCurrentIndex() + 1;
            infoPanel.updateInfo(desc, displayStep, animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
        } else {
            timer.stop(); 
            infoPanel.updateInfo("✓ Visualization Complete!", animationManager.getTotalSteps(), animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
        }
    }

    private void stepBackward() {
        timer.stop();
        if (animationManager.prevStep()) {
            vizPanel.repaint();
            AlgorithmStep step = animationManager.getCurrentStep();
            String desc = (step.getDescription() != null) ? step.getDescription() : "Step Backward";
            int displayStep = animationManager.getCurrentIndex() + 1;
            infoPanel.updateInfo(desc, displayStep, animationManager.getTotalSteps(), currentAlgoName, currentComplexity);
        }
    }
}