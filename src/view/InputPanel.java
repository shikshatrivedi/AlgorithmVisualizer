package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The panel where the user enters the array and selects an algorithm.
 */
public class InputPanel extends JPanel {
    private JTextField arrayInputField;
    private JTextField targetField;
    private JComboBox<String> algorithmSelector;
    private JButton startButton;

    public InputPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(230, 230, 230));

        // Array Input
        this.add(new JLabel("Array (comma separated):"));
        arrayInputField = new JTextField("10, 50, 20, 40, 30", 15);
        this.add(arrayInputField);

        // Target Input (for searching)
        this.add(new JLabel("Target (for Search):"));
        targetField = new JTextField("30", 5);
        this.add(targetField);

        // Algorithm Dropdown
        String[] algorithms = {
            "Bubble Sort", "Selection Sort", "Insertion Sort", 
            "Merge Sort", "Quick Sort", "Linear Search", "Binary Search"
        };
        algorithmSelector = new JComboBox<>(algorithms);
        this.add(algorithmSelector);

        // Start Button
        startButton = new JButton("Generate & Start");
        this.add(startButton);
    }

    // Getters for the Controller to read what the user typed
    public String getArrayInput() { return arrayInputField.getText(); }
    public String getTargetInput() { return targetField.getText(); }
    public String getSelectedAlgorithm() { return (String) algorithmSelector.getSelectedItem(); }
    
    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }
}