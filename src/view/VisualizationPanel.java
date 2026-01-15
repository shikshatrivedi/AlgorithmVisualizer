package view;

import model.AlgorithmStep;
import javax.swing.*;
import java.awt.*;

/**
 * The screen where the algorithm is drawn.
 * It converts the array numbers into colorful bars.
 */
public class VisualizationPanel extends JPanel {
    private AnimationManager animationManager;

    public VisualizationPanel(AnimationManager animationManager) {
        this.animationManager = animationManager;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        AlgorithmStep currentStep = animationManager.getCurrentStep();
        if (currentStep == null) return;

        int[] array = currentStep.getArraySnapshot();
        int[] highlights = currentStep.getHighlightedIndices();
        String type = currentStep.getActionType();

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / array.length;

        // Find max value to scale the bars correctly
        int maxValue = 0;
        for (int val : array) if (val > maxValue) maxValue = val;

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) (((double) array[i] / maxValue) * (height - 50));
            
            // Default color: Light Blue
            g.setColor(new Color(173, 216, 230));

            // Change color based on what's happening
            for (int h : highlights) {
                if (i == h) {
                    if (type.equals("COMPARE")) g.setColor(Color.YELLOW);
                    else if (type.equals("SWAP")) g.setColor(Color.RED);
                    else if (type.equals("HIGHLIGHT")) g.setColor(Color.ORANGE);
                    else if (type.equals("SORTED")) g.setColor(Color.GREEN);
                }
            }

            // Draw the bar
            int x = i * barWidth;
            int y = height - barHeight;
            g.fillRect(x + 2, y, barWidth - 4, barHeight);
            
            // Draw the number above/on the bar
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), x + (barWidth / 2) - 5, y - 5);
        }
    }
}