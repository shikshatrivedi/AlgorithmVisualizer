package view;

import model.AlgorithmStep;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Ellipse2D;

public class VisualizationPanel extends JPanel {
    private AnimationManager animationManager;
    
    // Cyberpunk Palette
    private final Color BG_COLOR = new Color(20, 20, 28);
    private final Color GRID_COLOR = new Color(255, 255, 255, 10);
    private final Color BAR_START = new Color(0, 200, 255);
    private final Color BAR_END = new Color(100, 50, 255);
    private final Color HIGHLIGHT = new Color(255, 100, 0); // Orange
    private final Color NODE_COLOR = new Color(40, 40, 50);
    private final Color LINE_COLOR = new Color(100, 100, 120);

    // Toggle for Tree Mode
    private boolean isTreeMode = false;

    public VisualizationPanel(AnimationManager am) {
        this.animationManager = am;
        this.setBackground(BG_COLOR);
    }

    public void setTreeMode(boolean isTree) {
        this.isTreeMode = isTree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2);
        
        // Border
        g2.setColor(new Color(60, 60, 80));
        g2.drawRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 30, 30);

        AlgorithmStep step = animationManager.getCurrentStep();
        if (step == null) {
            drawCenteredText(g2, "PRESS GENERATE TO START", getWidth() / 2, getHeight() / 2);
            return;
        }

        if (isTreeMode) {
            drawTree(g2, step.getArraySnapshot(), step.getHighlightedIndices());
        } else {
            drawBars(g2, step.getArraySnapshot(), step.getHighlightedIndices());
        }
    }

    // --- MODE 1: BARS (Sorting) ---
    private void drawBars(Graphics2D g2, int[] array, int[] highlights) {
        int n = array.length;
        int padding = 80;
        int availableWidth = getWidth() - (2 * padding);
        int barWidth = Math.max(10, (availableWidth / n) - 10);
        int maxVal = 0;
        for (int v : array) maxVal = Math.max(maxVal, v);

        for (int i = 0; i < n; i++) {
            int val = array[i];
            int barHeight = (int) (((double) val / maxVal) * (getHeight() - 250));
            int x = padding + i * (barWidth + 10);
            int y = getHeight() - barHeight - 100;

            Color c1 = BAR_START;
            Color c2 = BAR_END;

            if (highlights != null) {
                for (int h : highlights) {
                    if (h == i) { c1 = new Color(255, 50, 50); c2 = HIGHLIGHT; }
                }
            }

            GradientPaint gp = new GradientPaint(x, y, c1, x, y + barHeight, c2);
            g2.setPaint(gp);
            g2.fill(new RoundRectangle2D.Double(x, y, barWidth, barHeight, 15, 15));

            // Text
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 14));
            String text = String.valueOf(val);
            g2.drawString(text, x + (barWidth - g2.getFontMetrics().stringWidth(text)) / 2, y - 10);
        }
    }

    // --- MODE 2: TREE (Traversal) ---
    private void drawTree(Graphics2D g2, int[] array, int[] highlights) {
        if (array.length == 0) return;
        // Start recursive drawing from root (index 0)
        // Position root at center-top
        drawRecursiveNode(g2, array, highlights, 0, getWidth() / 2, 80, getWidth() / 4);
    }

    private void drawRecursiveNode(Graphics2D g2, int[] array, int[] highlights, int index, int x, int y, int xOffset) {
        if (index >= array.length) return;

        // Draw Left Link
        int leftIndex = 2 * index + 1;
        if (leftIndex < array.length) {
            g2.setColor(LINE_COLOR);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x, y + 20, x - xOffset, y + 80 - 20);
            drawRecursiveNode(g2, array, highlights, leftIndex, x - xOffset, y + 80, xOffset / 2);
        }

        // Draw Right Link
        int rightIndex = 2 * index + 2;
        if (rightIndex < array.length) {
            g2.setColor(LINE_COLOR);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x, y + 20, x + xOffset, y + 80 - 20);
            drawRecursiveNode(g2, array, highlights, rightIndex, x + xOffset, y + 80, xOffset / 2);
        }

        // Draw Current Node (Circle)
        int r = 40; // Diameter
        Color fill = NODE_COLOR;
        Color textC = Color.WHITE;

        if (highlights != null) {
            for (int h : highlights) {
                if (h == index) { fill = HIGHLIGHT; textC = Color.BLACK; }
            }
        }

        g2.setColor(fill);
        g2.fill(new Ellipse2D.Double(x - r/2, y - r/2, r, r));
        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Ellipse2D.Double(x - r/2, y - r/2, r, r));

        // Text
        g2.setColor(textC);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        String val = String.valueOf(array[index]);
        int tw = g2.getFontMetrics().stringWidth(val);
        g2.drawString(val, x - tw / 2, y + 6);
    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(GRID_COLOR);
        for (int i = 0; i < getWidth(); i += 50) g2.drawLine(i, 0, i, getHeight());
        for (int i = 0; i < getHeight(); i += 50) g2.drawLine(0, i, getWidth(), i);
    }

    private void drawCenteredText(Graphics2D g2, String text, int x, int y) {
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2.drawString(text, x - g2.getFontMetrics().stringWidth(text) / 2, y);
    }
}