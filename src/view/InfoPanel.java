package view;

import javax.swing.*;
import java.awt.*;

/**
 * Rich information panel displaying step progress, algorithm name,
 * description, and complexity information.
 */
public class InfoPanel extends JPanel {
    private JLabel stepLabel;
    private JLabel algoLabel;
    private JLabel descLabel;
    private JLabel complexityLabel;

    // Colors
    private final Color BG_DARK = new Color(22, 22, 32);
    private final Color ACCENT_GREEN = new Color(0, 255, 128);
    private final Color ACCENT_CYAN = new Color(0, 200, 255);
    private final Color TEXT_DIM = new Color(160, 160, 180);
    private final Color BORDER_COLOR = new Color(50, 50, 65);

    public InfoPanel() {
        this.setBackground(BG_DARK);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 15, 2, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Step Counter
        stepLabel = createInfoLabel("Step: — / —", ACCENT_GREEN, Font.BOLD, 15);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        add(stepLabel, gbc);

        // Vertical separator
        JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
        sep1.setForeground(BORDER_COLOR);
        sep1.setPreferredSize(new Dimension(2, 35));
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(sep1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridheight = 1;

        // Algorithm Name
        algoLabel = createInfoLabel("Algorithm: —", ACCENT_CYAN, Font.BOLD, 14);
        gbc.gridx = 2; gbc.gridy = 0;
        add(algoLabel, gbc);

        // Description
        descLabel = createInfoLabel("Ready to visualize...", TEXT_DIM, Font.PLAIN, 13);
        gbc.gridx = 2; gbc.gridy = 1;
        add(descLabel, gbc);

        // Vertical separator
        JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
        sep2.setForeground(BORDER_COLOR);
        sep2.setPreferredSize(new Dimension(2, 35));
        gbc.gridx = 3; gbc.gridy = 0; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(sep2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridheight = 1;

        // Complexity
        complexityLabel = createInfoLabel("Complexity: —", new Color(255, 200, 50), Font.BOLD, 13);
        gbc.gridx = 4; gbc.gridy = 0; gbc.gridheight = 2;
        add(complexityLabel, gbc);
    }

    private JLabel createInfoLabel(String text, Color fg, int style, int size) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", style, size));
        lbl.setForeground(fg);
        return lbl;
    }

    /**
     * Update the info panel with full algorithm context.
     */
    public void updateInfo(String description, int step, int total) {
        updateInfo(description, step, total, "", "");
    }

    /**
     * Full update with algorithm name and complexity.
     */
    public void updateInfo(String description, int step, int total, String algoName, String complexity) {
        stepLabel.setText(String.format("Step: %d / %d", step, total));
        descLabel.setText(description);

        if (algoName != null && !algoName.isEmpty()) {
            algoLabel.setText("Algorithm: " + algoName);
        }

        if (complexity != null && !complexity.isEmpty()) {
            complexityLabel.setText("⏱ " + complexity);
        }
    }
}