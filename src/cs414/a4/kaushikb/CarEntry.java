package cs414.a4.kaushikb;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CarEntry {
	public static JPanel carEntryTab = new JPanel();
	public static JPanel carEntryScreen1;
	
	static JTextField carPlateField = new JTextField(20);
	
	// Retrieves and returns add/remove car panel
	   static JPanel startup()
	   {   
		carEntryScreen1 = CarEntry.getcarEntryScreen1();
		carEntryTab.add(carEntryScreen1);

		carEntryScreen1.setVisible(true);

		return carEntryTab;
    }
	   static JPanel getcarEntryScreen1()
	    {   
			
		   carEntryScreen1 = new JPanel(new FlowLayout());
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
			JButton addButton = new JButton("Get Ticket");
			JButton clearButton = new JButton("Clear Data");

			addButton.setFont(textFont);
			//removeButton.setFont(textFont);
			clearButton.setFont(textFont);

			buttonPanel.add(Box.createHorizontalStrut(10));
			buttonPanel.add(addButton);
			//buttonPanel.add(removeButton);
			buttonPanel.add(clearButton);

			holderPanel.add(criteriaPanel);		
			generalPanel.add(holderPanel);
			holderPanel.add(Box.createVerticalStrut(75));
			generalPanel.add(buttonPanel);
			carEntryScreen1.add(generalPanel);
			carEntryScreen1.add(Box.createHorizontalStrut(100));

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
	                            AtomicInteger seq = new AtomicInteger();
	                            int nextVal = seq.incrementAndGet();
	                            int ticketNo = nextVal;
	                            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	                            Date dateobj = new Date();
	                            System.out.println(df.format(dateobj));

	                            /*getting current date time using calendar class 
	                             * An Alternative of above*/
	                            Calendar calobj = Calendar.getInstance();
	                            System.out.println(df.format(calobj.getTime()));
	                            ParkingTicket pTicket = new ParkingTicket(ticketNo,df.format(calobj.getTime()));
	                        	int another = JOptionPane.showConfirmDialog((Component) 
	                        	buttonPanel,"Please Take the Ticket"+ ticketNo +"   & Enter the Garage. Add another car to the lot?",
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
	       
	/* removeButton.addActionListener(new ActionListener()     // button listener for removing car from lot
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
	);*/

	 clearButton.addActionListener(new ActionListener()  // button listener for clear button
	 {   
	     public void actionPerformed(ActionEvent e)
	     {
		   carPlateField.setText("");  // reset text field
	     }
	 }
	);

	return carEntryScreen1;
	   }
	
}
