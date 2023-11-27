package com.mirs.view;

import javax.swing.UIManager;

/**
 * ViewDriver2.java
 *
 * Driver class that starts the GUI (View) of the FleetRental application.
 *
 * @author Mike.Prasad
 */
public class ViewDriver2 {

    /** Creates a new instance of ViewDriver2 */
    public ViewDriver2()
    {
        //set OS lookd and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Following MVC approach here.

        //create view0 class
        MainJFrame mainJFrame = new MainJFrame();

        // Create Controller class and set the view0
        MainJFrameController mainJFrameController = new MainJFrameController(mainJFrame);

    }

    /**
     * View entry point.
     *
     * @param args String[] - Arguments that can be passed to the Application
     */
    public static void main(String[] args) {
        new ViewDriver2();
    }

}
