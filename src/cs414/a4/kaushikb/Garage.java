package cs414.a4.kaushikb;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class Garage extends JFrame 
{
    public static JTabbedPane index;
    public static CarLot myCarLot;
    
      public Garage()
      {
        // setting window properties
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(3);
        this.setTitle("Car Parking System");
        this.setResizable(false);

        Color newColor = new Color(0.2f, 0.1f, 0.8f, 0.1f);

        // creating Car Lot object
        myCarLot = new CarLot(72, "carData.txt");

        // building tabbed panel display
        index = new JTabbedPane();
        index.setBackground(newColor);
        final JPanel statusTab = Status.startup();
        final JPanel addOrRemoveCarTab = AddOrRemoveCar.startup();
        final JPanel carEntryTab = CarEntry.startup();
        final JPanel carExitTab = CarExit.startup();
        
        // adding tabs to tabbed panel
        index.addTab("Parking Lot Status", statusTab);
        index.addTab("Add Or Remove Cars", addOrRemoveCarTab);
        index.addTab("Car Entry", carEntryTab);
        index.addTab("Car Exit", carExitTab);
        
        // setting content pane
        this.getContentPane().add(index);
      }

      public static void main(String[] args) 
      {
        // initialize frame and set visible
        Garage main = new Garage();
        main.setVisible(true);
      }
}
