package view;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel infoLabel;

    public InfoPanel() {
        this.setBackground(new Color(25, 25, 35));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(100, 100, 100)));

        infoLabel = new JLabel("Status: Ready to visualize...");
        infoLabel.setFont(new Font("Consolas", Font.PLAIN, 14)); // Code font
        infoLabel.setForeground(new Color(0, 255, 128)); // Matrix Green
        
        add(infoLabel);
    }

    public void updateInfo(String description, int step, int total) {
        infoLabel.setText(String.format("Step: %d/%d | %s", step, total, description));
    }
}