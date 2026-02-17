package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel extends JPanel {
    private JButton playButton, pauseButton, nextButton, prevButton, resetButton, restartButton;
    private JSlider speedSlider;
    private JLabel speedLabel;

    // Dark Theme Colors
    private final Color BG_DARK = new Color(25, 25, 35);
    private final Color BTN_BG = new Color(45, 45, 55);
    private final Color BTN_BORDER = new Color(80, 80, 100);
    private final Color BTN_HOVER_BG = new Color(0, 120, 215);
    private final Color BTN_HOVER_BORDER = new Color(0, 150, 255);
    private final Color RESET_HOVER_BG = new Color(200, 50, 50);
    private final Color RESET_HOVER_BORDER = new Color(255, 80, 80);

    public ControlPanel() {
        this.setBackground(BG_DARK);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 18));
        this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(50, 50, 60)));

// ... (constructor content updated in previous step) ...


        // 1. Playback Buttons
        prevButton  = createProButton(" PREV", "⏮");
        playButton  = createProButton(" PLAY", "▶");
        pauseButton = createProButton(" PAUSE", "❚❚");
        nextButton  = createProButton(" NEXT", "⏭");
        resetButton = createProButton(" RESET", "⟳");
        JButton restartButton = createProButton(" RESTART", "⚡"); 
        // We'll expose restartButton via a getter or listener method, 
        // but wait, the existing code didn't assign it to a field. 
        // Let's add it to the class fields first.
        this.restartButton = restartButton;

        // Special hover color for reset
        configureSpecialButton(resetButton, RESET_HOVER_BG, RESET_HOVER_BORDER);
        configureSpecialButton(restartButton, new Color(0, 180, 100), new Color(0, 200, 120));

        // 2. Speed Slider (Right = Faster)
        JLabel lblSpeed = new JLabel("SPEED: x1.0");
        lblSpeed.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSpeed.setForeground(new Color(160, 160, 180));
        this.speedLabel = lblSpeed; // Assign to field to update later

        speedSlider = new JSlider(0, 990, 500); // 0 to 990, default 500
        speedSlider.setBackground(BG_DARK);
        speedSlider.setForeground(new Color(0, 200, 255));
        speedSlider.setPreferredSize(new Dimension(180, 30));
        speedSlider.setToolTipText("Drag right for faster playback");
        
        speedSlider.addChangeListener(e -> {
            int val = speedSlider.getValue();
            // Map 0..990 to x1.0 .. x10.0 roughly
            // delay = 1000 - val. 
            // If val=0, delay=1000 (1 step/sec) -> x1.0
            // If val=900, delay=100 (10 steps/sec) -> x10.0
            double multiplier = 1000.0 / (1000.0 - val);
            if (multiplier > 10) multiplier = 10;
            lblSpeed.setText(String.format("SPEED: x%.1f", multiplier));
        });

        this.add(prevButton);
        this.add(playButton);
        this.add(pauseButton);
        this.add(nextButton);
        this.add(resetButton);
        this.add(restartButton);
        this.add(Box.createHorizontalStrut(10));
        this.add(lblSpeed);
        this.add(speedSlider);
    }

    private void configureSpecialButton(JButton btn, Color hoverBg, Color hoverBorder) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverBg);
                btn.setBorder(BorderFactory.createLineBorder(hoverBorder, 2));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BTN_BG);
                btn.setBorder(BorderFactory.createLineBorder(BTN_BORDER, 2));
            }
        });
    }

    private JButton createProButton(String text, String icon) {
        JButton btn = new JButton(icon + text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(BTN_BG);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(BTN_BORDER, 2));
        btn.setPreferredSize(new Dimension(130, 48));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BTN_HOVER_BG);
                btn.setBorder(BorderFactory.createLineBorder(BTN_HOVER_BORDER, 2));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BTN_BG);
                btn.setBorder(BorderFactory.createLineBorder(BTN_BORDER, 2));
            }
        });

        return btn;
    }

    // --- Listener Methods ---
    public void addPlayListener(java.awt.event.ActionListener l)  { playButton.addActionListener(l); }
    public void addPauseListener(java.awt.event.ActionListener l) { pauseButton.addActionListener(l); }
    public void addNextListener(java.awt.event.ActionListener l)  { nextButton.addActionListener(l); }
    public void addPrevListener(java.awt.event.ActionListener l)  { prevButton.addActionListener(l); }
    public void addResetListener(java.awt.event.ActionListener l) { resetButton.addActionListener(l); }
    public void addRestartListener(java.awt.event.ActionListener l) { restartButton.addActionListener(l); }
    
    public int getSpeed() { return speedSlider.getValue(); }
    public void addSpeedListener(javax.swing.event.ChangeListener l) { speedSlider.addChangeListener(l); }
}