package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel to display text descriptions of the current algorithm step.
 */
public class InfoPanel extends JPanel {
    private JLabel statusLabel;
    private JLabel stepCounterLabel;

    public InfoPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.setBackground(Color.DARK_GRAY);

        statusLabel = new JLabel("Enter an array and press Start");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

        stepCounterLabel = new JLabel("Step: 0/0");
        stepCounterLabel.setForeground(Color.WHITE);

        this.add(statusLabel, BorderLayout.WEST);
        this.add(stepCounterLabel, BorderLayout.EAST);
    }

    public void updateInfo(String message, int currentStep, int totalSteps) {
        statusLabel.setText(message);
        stepCounterLabel.setText("Step: " + currentStep + "/" + totalSteps);
    }
}