import controller.MainController;
import view.*;
import javax.swing.*;
import java.awt.*;

/**
 * This is the starting point of the entire application.
 * It assembles the View and the Controller together.
 */
public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (standard Java practice)
        SwingUtilities.invokeLater(() -> {
            // 1. Setup the Frame (The Window)
            JFrame frame = new JFrame("Algorithm Visualizer 1.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // 2. Initialize View Components
            AnimationManager animationManager = new AnimationManager();
            VisualizationPanel vizPanel = new VisualizationPanel(animationManager);
            InputPanel inputPanel = new InputPanel();
            ControlPanel controlPanel = new ControlPanel();
            InfoPanel infoPanel = new InfoPanel();

            // 3. Add Panels to the Frame
            frame.add(inputPanel, BorderLayout.NORTH);   // Top: Inputs
            frame.add(vizPanel, BorderLayout.CENTER);    // Middle: The Bars
            
            // A sub-panel to hold both Controls and Info at the bottom
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(controlPanel, BorderLayout.NORTH);
            bottomPanel.add(infoPanel, BorderLayout.SOUTH);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            // 4. Start the Controller (The Brain)
            new MainController(animationManager, vizPanel, inputPanel, controlPanel, infoPanel);

            // 5. Finalize the Window
            frame.pack();
            frame.setLocationRelativeTo(null); // Centers the window on screen
            frame.setVisible(true);
        });
    }
}