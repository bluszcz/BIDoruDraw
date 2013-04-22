package pl.idoru.draw;

import javax.swing.*;

public class PanelExample{

    public JPanel createContentPane(){
        // This is where we'll put all our widgets
        // in the next tutorials!
        JPanel anything = new JPanel();
        anything.setSize(800, 800);
        //content panes must be opaque
        anything.setOpaque(true); 
        return anything;  
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] There's a JPanel in here! [=]");

        //Create and set up the content pane.
        PanelExample demo = new PanelExample();
        frame.setContentPane(demo.createContentPane());

        // The other bits and pieces that make our program a bit more stable.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(10, 10);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
