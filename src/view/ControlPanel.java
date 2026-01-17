package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel extends JPanel {
    private JButton playButton, pauseButton, nextButton, prevButton;
    private JSlider speedSlider;

    public ControlPanel() {
        this.setBackground(new Color(25, 25, 35));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 25)); // Huge spacing
        this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(50, 50, 60)));

        // 1. Create Massive Buttons
        prevButton = createProButton(" PREV", "⏮");
        playButton = createProButton(" PLAY", "▶");
        pauseButton = createProButton(" PAUSE", "❚❚");
        nextButton = createProButton(" NEXT", "⏭");

        // 2. Speed Slider
        JLabel lblSpeed = new JLabel("SPEED");
        lblSpeed.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSpeed.setForeground(Color.GRAY);
        
        speedSlider = new JSlider(10, 1000, 500);
        speedSlider.setBackground(new Color(25, 25, 35));
        speedSlider.setPreferredSize(new Dimension(200, 30));

        this.add(prevButton);
        this.add(playButton);
        this.add(pauseButton);
        this.add(nextButton);
        this.add(lblSpeed);
        this.add(speedSlider);
    }

    private JButton createProButton(String text, String icon) {
        JButton btn = new JButton(icon + text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Big Font
        btn.setBackground(new Color(45, 45, 55));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100), 2));
        btn.setPreferredSize(new Dimension(140, 55)); // MASSIVE SIZE
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Interaction Logic
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 120, 215)); // Windows Blue Highlight
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 255), 2));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(45, 45, 55));
                btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100), 2));
            }
        });

        return btn;
    }

    // Listeners (Keep these exactly the same so Controller works)
    public void addPlayListener(java.awt.event.ActionListener l) { playButton.addActionListener(l); }
    public void addPauseListener(java.awt.event.ActionListener l) { pauseButton.addActionListener(l); }
    public void addNextListener(java.awt.event.ActionListener l) { nextButton.addActionListener(l); }
    public void addPrevListener(java.awt.event.ActionListener l) { prevButton.addActionListener(l); }
    public int getSpeed() { return speedSlider.getValue(); }
    public void addSpeedListener(javax.swing.event.ChangeListener l) { speedSlider.addChangeListener(l); }
}