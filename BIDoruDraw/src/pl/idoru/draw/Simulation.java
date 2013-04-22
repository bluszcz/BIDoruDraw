package pl.idoru.draw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
import pl.idoru.draw.JavaPaintUI;


public class Simulation implements Runnable {
 
    @Override
    public void run() {
        // Create the window
        JFrame f = new JavaPaintUI();
        //JPanel myJPanel = new JPanel();
        //myJPanel.setPreferredSize(new Dimension(500,500));
        //f.add(myJPanel);
        //f.pack();
        
        
        // Sets the behavior for when the window is closed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add a layout manager so that the button is not placed on top of the label
        f.setLayout(new FlowLayout());
        // Add a label and a button
        //f.add(new JLabel("Simulate me"));
        //+++f.add(new JButton("FAKE BUTTON"));
        // Arrange the components inside the window
        f.pack();
        // By default, the window is not visible. Make it visible.
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Simulation se = new Simulation();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }
 
}