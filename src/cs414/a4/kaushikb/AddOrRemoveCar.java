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
import javax.swing.SwingConstants;

class AddOrRemoveCar
{
	public static JPanel addOrRemoveCarTab = new JPanel();
	public static JPanel addOrRemoveCarScreen1;

	static JTextField carPlateField = new JTextField(20);

	// Retrieves and returns add/remove car panel
	   static JPanel startup()
	   {   
	    addOrRemoveCarScreen1 = AddOrRemoveCar.getAddOrRemoveCarScreen1();
		addOrRemoveCarTab.add(addOrRemoveCarScreen1);

		addOrRemoveCarScreen1.setVisible(true);

		return addOrRemoveCarTab;
       }

	// Defines and returns graphical components for screen
	static JPanel getAddOrRemoveCarScreen1()
    {   
		
        addOrRemoveCarScreen1 = new JPanel(new FlowLayout());
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));
		generalPanel.add(Box.createVerticalStrut(170));

		JPanel holderPanel = new JPanel(new BorderLayout());
		holderPanel.setLayout(new BoxLayout(holderPanel, BoxLayout.X_AXIS));
		JPanel criteriaPanel = new JPanel(new FlowLayout());
        JLabel carPlateLabel = new JLabel("License Plate Number:", 
        SwingConstants.RIGHT);

		Font textFont = new Font("SanSerif", Font.PLAIN, 24);
		Font textFieldFont = new Font("Serif", Font.PLAIN, 20);

		carPlateLabel.setFont(textFont);
		carPlateField.setFont(textFieldFont);

		criteriaPanel.add(carPlateLabel);
		criteriaPanel.add(carPlateField);

		final JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton addButton = new JButton("Add Car to Parking Lot");
		JButton removeButton = new JButton("Remove Car from Parking Lot");
		JButton clearButton = new JButton("Clear Data");

		addButton.setFont(textFont);
		removeButton.setFont(textFont);
		clearButton.setFont(textFont);

		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(clearButton);

		holderPanel.add(criteriaPanel);		
		generalPanel.add(holderPanel);
		holderPanel.add(Box.createVerticalStrut(75));
		generalPanel.add(buttonPanel);
		addOrRemoveCarScreen1.add(generalPanel);
		addOrRemoveCarScreen1.add(Box.createHorizontalStrut(100));

		addButton.addActionListener(new ActionListener()   // button listener for adding car to lot
		{
            public void actionPerformed(ActionEvent e)
              {   
                 String carPlate = carPlateField.getText().trim();     // check validity of input	
   
                    if((carPlate.length() == 0))
                        {
                         JOptionPane.showMessageDialog((Component) buttonPanel, 
                         "Please fill in the field and try again",
                         "Blank Field", 
                         JOptionPane.ERROR_MESSAGE);
                        }
                    else
                        {
                      // perform enter operation
                      boolean result = Garage.myCarLot.carEnter(carPlate);    
                      
                        if(!result)   // check outcome and report results
                        {
                          JOptionPane.showMessageDialog((Component) buttonPanel, 
                          "This car plate is either not registered or is already in the lot. " +
                          "Please try again.","Invalid Operation", 
                          JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                          int another = JOptionPane.showConfirmDialog((Component) 
                          buttonPanel,"The car has been added. Add another car to the lot?",
                          "Add Car",
                          JOptionPane.YES_NO_OPTION);

                          carPlateField.setText("");    // reset input field						
                            if(another == JOptionPane.NO_OPTION)  // change tabs based on user input
                            {
                                Garage.index.setSelectedIndex(0);
                            }	
                            
                        }					
                    }
                }
        });
       
 removeButton.addActionListener(new ActionListener()     // button listener for removing car from lot
 {
  public void actionPerformed(ActionEvent e)
    {
   String carPlate = carPlateField.getText().trim();	 // retrieve input data      
      if((carPlate.length() == 0))  // check data validity
      {
        // invalid
        JOptionPane.showMessageDialog((Component) buttonPanel, 
        "Please fill in the field and try again",
        "Blank Field", JOptionPane.ERROR_MESSAGE);
      }
        else
      {
         // valid
	     boolean result = Garage.myCarLot.carExit(carPlate);  // system will perform exit operation
	  	if(!result) // check outcome and report results
	  {
		 JOptionPane.showMessageDialog((Component) buttonPanel, 
		 "This license plate is invalid or is already in the lot. Please try again.",
		 "Invalid Operation", JOptionPane.ERROR_MESSAGE);
	  }
         else
      {
		 int another = JOptionPane.showConfirmDialog((Component)
		 buttonPanel, "The car has been removed. Remove another car?", 
		 "Add Car",
         JOptionPane.YES_NO_OPTION); 
         carPlateField.setText("");
         
      if(another == JOptionPane.NO_OPTION)
      {   
          Garage.index.setSelectedIndex(0);
      }						
     }					
    }
   }
 }
);

 clearButton.addActionListener(new ActionListener()  // button listener for clear button
 {   
     public void actionPerformed(ActionEvent e)
     {
	   carPlateField.setText("");  // reset text field
     }
 }
);

return addOrRemoveCarScreen1;
   }
 }
