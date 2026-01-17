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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // Import needed for auto-sorting
import java.util.List;

public class MainController {
    private AnimationManager animationManager;
    private VisualizationPanel vizPanel;
    private InputPanel inputPanel;
    private ControlPanel controlPanel;
    private InfoPanel infoPanel;
    private Timer timer;

    public MainController(AnimationManager am, VisualizationPanel vp, InputPanel ip, ControlPanel cp, InfoPanel info) {
        this.animationManager = am;
        this.vizPanel = vp;
        this.inputPanel = ip;
        this.controlPanel = cp;
        this.infoPanel = info;

        // 1. Initialize the Timer (The Heartbeat)
        timer = new Timer(controlPanel.getSpeed(), e -> stepForward());

        // 2. Connect the "Generate" Button
        inputPanel.addGenerateListener(e -> handleGenerate());

        // 3. Connect Playback Controls
        controlPanel.addPlayListener(e -> play());
        controlPanel.addPauseListener(e -> pause());
        controlPanel.addNextListener(e -> stepForward());
        controlPanel.addPrevListener(e -> stepBackward());
        
        // 4. Connect Speed Slider
        controlPanel.addSpeedListener(e -> {
            int newSpeed = controlPanel.getSpeed();
            timer.setDelay(newSpeed);
        });
    }

    // --- Action Handlers ---

    private void handleGenerate() {
        try {
            // Get inputs from the UI
            String arrayStr = inputPanel.getArrayInput();
            String targetStr = inputPanel.getTargetInput();
            String algoType = inputPanel.getSelectedAlgorithm();

            // Parse inputs
            int[] data = parseArray(arrayStr);
            int target = targetStr.isEmpty() ? 0 : Integer.parseInt(targetStr.trim());

            AlgorithmStrategy algorithm;
            
            // IMPORTANT: Reset Tree Mode by default (assume we are doing bars)
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
                    // AUTO-FIX: Binary Search requires sorted data to work!
                    Arrays.sort(data); 
                    algorithm = new BinarySearch(); 
                    break;
                
                // --- TREE MODE LOGIC ---
                case "Inorder Traversal (Tree)": 
                    algorithm = new InorderTraversal(); 
                    vizPanel.setTreeMode(true); // Tell View to draw circles!
                    break;
                    
                default: algorithm = new BubbleSort(); break;
            }

            // Generate the animation steps
            List<AlgorithmStep> steps = algorithm.generateSteps(data, target);
            animationManager.setSteps(steps);
            
            // Get Complexity Info
            String complexity = getComplexity(algoType);

            // Reset UI with extra info
            vizPanel.repaint();
            infoPanel.updateInfo("Loaded: " + algoType + " | Time Complexity: " + complexity, 0, steps.size());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Please enter valid numbers (e.g., '10, 20, 30')");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private int[] parseArray(String input) {
        String[] parts = input.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        return arr;
    }

    // Helper method for complexity
    private String getComplexity(String algo) {
        switch (algo) {
            case "Bubble Sort": return "O(n²)";
            case "Selection Sort": return "O(n²)";
            case "Insertion Sort": return "O(n²)";
            case "Merge Sort": return "O(n log n)";
            case "Quick Sort": return "O(n log n)";
            case "Linear Search": return "O(n)";
            case "Binary Search": return "O(log n)";
            case "Inorder Traversal (Tree)": return "O(n)";
            default: return "Unknown";
        }
    }

    // --- Playback Logic ---

    private void play() {
        timer.start();
        infoPanel.updateInfo("Playing...", animationManager.getCurrentIndex(), animationManager.getTotalSteps());
    }

    private void pause() {
        timer.stop();
        infoPanel.updateInfo("Paused.", animationManager.getCurrentIndex(), animationManager.getTotalSteps());
    }

    private void stepForward() {
        if (animationManager.nextStep()) {
            vizPanel.repaint();
            AlgorithmStep step = animationManager.getCurrentStep();
            
            // Use the advanced description if available
            String desc = (step.getDescription() != null) ? step.getDescription() : "Step Forward";
            infoPanel.updateInfo(desc, animationManager.getCurrentIndex(), animationManager.getTotalSteps());
        } else {
            timer.stop(); 
            infoPanel.updateInfo("Visualization Complete!", animationManager.getTotalSteps(), animationManager.getTotalSteps());
        }
    }

    private void stepBackward() {
        timer.stop();
        if (animationManager.prevStep()) {
            vizPanel.repaint();
            infoPanel.updateInfo("Step Backward", animationManager.getCurrentIndex(), animationManager.getTotalSteps());
        }
    }
}