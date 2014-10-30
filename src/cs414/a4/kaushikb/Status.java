package cs414.a4.kaushikb;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Status
{
	public static JPanel statusTab = new JPanel();
	public static JPanel statusScreen1;

	static JTextField carPlateField = new JTextField(20);

	// retrieves Status panel and sets visible
	static JPanel startup()
	{	
		statusScreen1 = Status.getStatusScreen1();
		statusTab.add(statusScreen1);

		statusScreen1.setVisible(true);

		return statusTab;

	}

  //defines and retrieves Status panel
	static JPanel getStatusScreen1()
    {   
	  statusScreen1 = new JPanel(new FlowLayout());
      JPanel generalPanel = new JPanel();
	  generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));
	  generalPanel.add(Box.createVerticalStrut(170));

	  JPanel holderPanel = new JPanel(new BorderLayout());
	  holderPanel.setLayout(new BoxLayout(holderPanel,BoxLayout.Y_AXIS));
	  JPanel criteriaPanel = new JPanel();
	  criteriaPanel.setLayout(new BoxLayout(criteriaPanel,BoxLayout.X_AXIS));
	  JLabel carPlateLabel = new JLabel("License Plate Number:");

	  Font textFont = new Font("SanSerif", Font.PLAIN, 24);
	  Font textFieldFont = new Font("Serif", Font.PLAIN, 20);

	  carPlateLabel.setFont(textFont);
	  carPlateField.setFont(textFieldFont);

		criteriaPanel.add(Box.createHorizontalStrut(40));
		criteriaPanel.add(carPlateLabel);
		criteriaPanel.add(carPlateField);
		criteriaPanel.add(Box.createHorizontalStrut(40));

		final JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		JButton lotCapacityButton = new JButton("Check Lot Capacity");
		JButton saveStateButton = new JButton("Save Lot State");
		JButton findSpaceButton = new JButton("Locate Vehicle");

		JButton clearButton = new JButton(" Clear  "); 
		lotCapacityButton.setFont(textFont);
		saveStateButton.setFont(textFont);
		findSpaceButton.setFont(textFont);
		clearButton.setFont(textFont);

		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(lotCapacityButton);	
		buttonPanel.add(saveStateButton);
		buttonPanel.add(findSpaceButton);
		buttonPanel.add(clearButton);

		holderPanel.add(criteriaPanel);
		holderPanel.add(Box.createVerticalStrut(30));
		holderPanel.add(buttonPanel);
		generalPanel.add(holderPanel);
		statusScreen1.add(generalPanel);
		statusScreen1.add(Box.createHorizontalStrut(150));


	  // button listener for lot capacity
		lotCapacityButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {   
			 // Retrieve required information
		     String carPlate = carPlateField.getText().trim();

			 int totalCapacity = Garage.myCarLot.getMaxSize();
			 int currentlyOccupied = Garage.myCarLot.carCount();
			 int freeSpace = totalCapacity - currentlyOccupied;

			 // Print dialog box
			 JOptionPane.showMessageDialog((Component) buttonPanel, 
			 "Total Capacity: " + totalCapacity +
			 "\nCurrently Occupied: " + currentlyOccupied +
			 "\nFree Space: " + freeSpace,
			 "Current Car Lot Statistics", 
             JOptionPane.INFORMATION_MESSAGE);
				
             // reset active tab and field data
             Garage.index.setSelectedIndex(0);
             carPlateField.setText("");						
          }
		});

	  // button listener for save state
		saveStateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{   
				// perform save operation
				int result = Garage.myCarLot.saveData();

				    // check if successful and report results
				    if(result == 0)
    				{
				        JOptionPane.showMessageDialog((Component) buttonPanel, 
				        "Data for all registered users has been updated in file: " +
				        Garage.myCarLot.getDataFileName(),
				        "Data Stored Successfully", JOptionPane.INFORMATION_MESSAGE);
				    }
				    else
				    {
				        JOptionPane.showMessageDialog((Component) buttonPanel, 
				        "Data could not be stored!",
				        "Data Extract Failure", JOptionPane.ERROR_MESSAGE);
				    }

				// reset active tab and field data
				Garage.index.setSelectedIndex(0);
				carPlateField.setText("");						
			}
		});

	  // button listener for car location search
		findSpaceButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
			{   
			// retrieve input and perform search
			String carPlate = carPlateField.getText().trim();
			String SpaceNumber = Garage.myCarLot.findSpaceLocation(carPlate);

			// check operation result and report using dialog boxes
			    if(!SpaceNumber.equals(""))
    			{
			         JOptionPane.showMessageDialog((Component) buttonPanel, 
			         "Location of car #" + carPlate + ":" +
			         "\nSpace " + SpaceNumber,
			         "Car Location Found", 
                     JOptionPane.INFORMATION_MESSAGE);
            
                }
                 else
                {
                    JOptionPane.showMessageDialog((Component)  buttonPanel, 
                    "Location of car #" + carPlate + ":" + 
                    "\nCould not be found." + "The vehicle is either not registered or not currently parked.", 
                    "Car Location Found", 
                    JOptionPane.ERROR_MESSAGE);
                }				
				
			// reset active tab and field data
			Garage.index.setSelectedIndex(0);
			carPlateField.setText("");						
          }
        });		

		// button listener for clear
		clearButton.addActionListener(new ActionListener()
		{   
		  public void actionPerformed(ActionEvent e)
		  {
			// reset license plate field
			carPlateField.setText("");
          }
		});

	  return statusScreen1;
    }
}
