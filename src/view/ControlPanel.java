package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The panel containing the playback controls (Play, Pause, Speed).
 */
public class ControlPanel extends JPanel {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JButton prevButton;
    private JSlider speedSlider;

    public ControlPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(240, 240, 240));

        // Create the buttons
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        nextButton = new JButton("Next Step");
        prevButton = new JButton("Previous Step");

        // Create speed slider (from 10ms to 1000ms delay)
        JLabel speedLabel = new JLabel("Delay (ms):");
        speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 1000, 500);
        speedSlider.setMajorTickSpacing(200);
        speedSlider.setPaintTicks(true);

        // Add everything to this panel
        this.add(prevButton);
        this.add(playButton);
        this.add(pauseButton);
        this.add(nextButton);
        this.add(speedLabel);
        this.add(speedSlider);
    }

    // These methods allow the Controller to "listen" for clicks
    public void addPlayListener(ActionListener listener) { playButton.addActionListener(listener); }
    public void addPauseListener(ActionListener listener) { pauseButton.addActionListener(listener); }
    public void addNextListener(ActionListener listener) { nextButton.addActionListener(listener); }
    public void addPrevListener(ActionListener listener) { prevButton.addActionListener(listener); }
    
    public JSlider getSpeedSlider() { return speedSlider; }
}