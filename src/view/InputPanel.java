package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemListener;
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
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(18, 20, 18, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Algorithm Selector
        String[] algos = {
            "Bubble Sort", "Selection Sort", "Insertion Sort",
            "Merge Sort", "Quick Sort",
            "Linear Search", "Binary Search",
            "Array Tree Traversal"
        };
        algoSelector = new JComboBox<>(algos);
        algoSelector.setFont(new Font("Segoe UI", Font.BOLD, 15));
        algoSelector.setBackground(TEXT_FIELD_BG);
        algoSelector.setForeground(Color.WHITE);
        algoSelector.setPreferredSize(new Dimension(200, 42));

        // 2. Array Input
        JLabel lblArray = createLabel("Input Array:");
        arrayInput = createStyledTextField("15, 30, 10, 50, 25", 18);

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

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        add(arrayInput, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0;
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
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 48));
    }

    // --- Public API ---
    public String getArrayInput() { return arrayInput.getText(); }
    public String getTargetInput() { return targetInput.getText(); }
    public String getSelectedAlgorithm() { return (String) algoSelector.getSelectedItem(); }
    public void addGenerateListener(java.awt.event.ActionListener l) { generateButton.addActionListener(l); }

    /** Enable/disable the target input field. */
    public void setTargetEnabled(boolean enabled) {
        targetInput.setEnabled(enabled);
        targetInput.setBackground(enabled ? TEXT_FIELD_BG : new Color(30, 30, 38));
        targetInput.setForeground(enabled ? NEON_BLUE : new Color(80, 80, 90));
    }

    /** Listen for algorithm combo-box selection changes. */
    public void addAlgoChangeListener(ItemListener l) {
        algoSelector.addItemListener(l);
    }
}