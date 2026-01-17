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

            // Layout
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(vizPanel, BorderLayout.CENTER);
            
            JPanel bottomContainer = new JPanel(new BorderLayout());
            bottomContainer.add(controlPanel, BorderLayout.NORTH);
            bottomContainer.add(infoPanel, BorderLayout.SOUTH);
            frame.add(bottomContainer, BorderLayout.SOUTH);

            // Start Controller
            new MainController(am, vizPanel, inputPanel, controlPanel, infoPanel);

            // Final Display Settings
            frame.setPreferredSize(new Dimension(1200, 800));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}