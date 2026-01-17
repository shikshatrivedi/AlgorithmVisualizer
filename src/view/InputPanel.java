package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class InputPanel extends JPanel {
    private JTextField arrayInput;
    private JTextField targetInput;
    private JButton generateButton;
    private JComboBox<String> algoSelector;

    // Modern Color Palette
    private final Color BG_DARK = new Color(25, 25, 35);
    private final Color TEXT_FIELD_BG = new Color(40, 40, 50);
    private final Color NEON_BLUE = new Color(0, 200, 255);
    private final Color TEXT_COLOR = new Color(230, 230, 230);

    public InputPanel() {
        this.setBackground(BG_DARK);
        this.setLayout(new GridBagLayout()); // Using GridBag for precise centering
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Generous padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Algorithm Selector (Big & Bold)
       String[] algos = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Linear Search", "Binary Search","Inorder Traversal (Tree)"};
        algoSelector = new JComboBox<>(algos);
        algoSelector.setFont(new Font("Segoe UI", Font.BOLD, 16));
        algoSelector.setBackground(TEXT_FIELD_BG);
        algoSelector.setForeground(Color.WHITE);
        algoSelector.setPreferredSize(new Dimension(200, 45));
        
        // 2. Array Input
        JLabel lblArray = createLabel("Input Array:");
        arrayInput = createStyledTextField("15, 30, 10, 50, 25", 20);

        // 3. Target Input
        JLabel lblTarget = createLabel("Target:");
        targetInput = createStyledTextField("30", 5);

        // 4. THE "HERO" BUTTON
        generateButton = new JButton("⚡ INITIALIZE SYSTEM") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient Background
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 100, 255), getWidth(), getHeight(), new Color(0, 200, 255));
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
                
                // Text
                super.paintComponent(g);
            }
        };
        styleHeroButton(generateButton);

        // --- LAYOUT ARRANGEMENT ---
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblArray, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(arrayInput, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        add(lblTarget, gbc);

        gbc.gridx = 3; gbc.gridy = 0;
        add(targetInput, gbc);

        gbc.gridx = 4; gbc.gridy = 0;
        add(algoSelector, gbc);

        gbc.gridx = 5; gbc.gridy = 0;
        add(generateButton, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(new Color(180, 180, 180));
        return lbl;
    }

    private JTextField createStyledTextField(String text, int cols) {
        JTextField field = new JTextField(text, cols);
        field.setFont(new Font("Consolas", Font.PLAIN, 18)); // Large Monospace font
        field.setBackground(TEXT_FIELD_BG);
        field.setForeground(NEON_BLUE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 80), 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private void styleHeroButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false); // Important for custom gradient
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 50)); // BIG BUTTON
    }

    // Getters
    public String getArrayInput() { return arrayInput.getText(); }
    public String getTargetInput() { return targetInput.getText(); }
    public String getSelectedAlgorithm() { return (String) algoSelector.getSelectedItem(); }
    public void addGenerateListener(java.awt.event.ActionListener l) { generateButton.addActionListener(l); }
}