package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel algoBadge;
    private JPanel legendPanel;

    private final Color BG_DARK = new Color(18, 18, 24);
    private final Color ACCENT_BLUE = new Color(0, 200, 255);
    private final Color BADGE_BG = new Color(40, 40, 50);

    public HeaderPanel() {
        this.setBackground(BG_DARK);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(15, 20, 15, 20));

        // 1. Title
        titleLabel = new JLabel("ALGO-VISION PRO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        // 2. Center: Algorithm Badge
        algoBadge = new JLabel(" Select Algorithm ");
        algoBadge.setFont(new Font("Segoe UI", Font.BOLD, 14));
        algoBadge.setForeground(ACCENT_BLUE);
        algoBadge.setOpaque(true);
        algoBadge.setBackground(BADGE_BG);
        algoBadge.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 80), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(algoBadge);

        // 3. Right: Legend
        legendPanel = createLegend();

        this.add(titleLabel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(legendPanel, BorderLayout.EAST);
    }

    private JPanel createLegend() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panel.setOpaque(false);

        panel.add(createLegendItem(new Color(255, 50, 50), "Compare/Swap"));
        panel.add(createLegendItem(new Color(0, 200, 255), "Sorted/Active"));

        return panel;
    }

    private JPanel createLegendItem(Color color, String text) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        item.setOpaque(false);

        JPanel colorBox = new JPanel();
        colorBox.setPreferredSize(new Dimension(12, 12));
        colorBox.setBackground(color);
        
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(180, 180, 180));

        item.add(colorBox);
        item.add(lbl);
        return item;
    }

    public void setAlgorithmName(String name) {
        algoBadge.setText(name.toUpperCase());
    }
}
