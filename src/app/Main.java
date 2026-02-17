package app;

import controller.MainController;
import view.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // GLOBAL SETTINGS FOR BEAUTY
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ALGO-VISION PRO v2.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            // Set Dark Background for the whole frame
            frame.getContentPane().setBackground(new Color(18, 18, 24));

            // Initialize Components
            AnimationManager am = new AnimationManager();
            VisualizationPanel vizPanel = new VisualizationPanel(am);
            InputPanel inputPanel = new InputPanel();
            ControlPanel controlPanel = new ControlPanel();
            InfoPanel infoPanel = new InfoPanel();
            HeaderPanel headerPanel = new HeaderPanel();

            // Layout
            // Top: Header
            frame.add(headerPanel, BorderLayout.NORTH);
            
            // Center Container: Input + Visualization
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.add(inputPanel, BorderLayout.NORTH); // Input below header
            centerPanel.add(vizPanel, BorderLayout.CENTER);
            frame.add(centerPanel, BorderLayout.CENTER);
            
            // Bottom Container: Controls + Info
            JPanel bottomContainer = new JPanel(new BorderLayout());
            bottomContainer.setBackground(new Color(18, 18, 24));
            bottomContainer.add(controlPanel, BorderLayout.NORTH);
            bottomContainer.add(infoPanel, BorderLayout.SOUTH);
            frame.add(bottomContainer, BorderLayout.SOUTH);

            // Start Controller
            MainController controller = new MainController(am, vizPanel, inputPanel, controlPanel, infoPanel, headerPanel);
            
            // Verify and setup keyboard controls
            controller.setupKeyboardControls(frame);

            // Final Display Settings
            frame.setPreferredSize(new Dimension(1200, 850));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setFocusable(true);
            frame.requestFocus();
        });
    }
}
