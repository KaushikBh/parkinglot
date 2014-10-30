package cs414.a4.kaushikb;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CarExit {
	public static JPanel carExitTab = new JPanel();
	public static JPanel carExitScreen1;
	
	static JTextField ticketNumberField = new JTextField(20);
	static JTextField carPlateField = new JTextField(20);
	
	// Retrieves and returns add/remove car panel
	   static JPanel startup()
	   {   
		carExitScreen1 = CarExit.getcarExitScreen1();
		carExitTab.add(carExitScreen1);

		carExitScreen1.setVisible(true);

		return carExitTab;
    }
	   static JPanel getcarExitScreen1()
	    {   
			
		   carExitScreen1 = new JPanel(new FlowLayout());
	        JPanel generalPanel = new JPanel();
	        generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));
			generalPanel.add(Box.createVerticalStrut(170));

			JPanel holderPanel = new JPanel(new BorderLayout());
			holderPanel.setLayout(new BoxLayout(holderPanel, BoxLayout.X_AXIS));
			JPanel criteriaPanel = new JPanel(new FlowLayout());
	        JLabel carParkingTicketLabel = new JLabel("Enter Ticket:", 
	        SwingConstants.RIGHT);
	        Font textFont = new Font("SanSerif", Font.PLAIN, 24);
			Font textFieldFont = new Font("Serif", Font.PLAIN, 20);
			
			JLabel carPlateLabel = new JLabel("Enter LicenseNumber:", 
			        SwingConstants.RIGHT);
			
			carParkingTicketLabel.setFont(textFont);
			ticketNumberField.setFont(textFieldFont);
			
			carPlateLabel.setFont(textFont);
			carPlateField.setFont(textFieldFont);
			
			criteriaPanel.add(carParkingTicketLabel);
			criteriaPanel.add(ticketNumberField);
			criteriaPanel.add(carPlateLabel);
			criteriaPanel.add(carPlateField);
			
			final JPanel buttonPanel = new JPanel(new FlowLayout());
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			JButton addButton = new JButton("Enter Payment");
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
			carExitScreen1.add(generalPanel);
			carExitScreen1.add(Box.createHorizontalStrut(100));

			addButton.addActionListener(new ActionListener()   // button listener for adding car to lot
			{
	            public void actionPerformed(ActionEvent e)
	              {   
	                 String ticketNumber = ticketNumberField.getText().trim();     // check validity of input	
	   
	                    if((ticketNumber.length() == 0))
	                        {
	                         JOptionPane.showMessageDialog((Component) buttonPanel, 
	                         "Please fill in the field and try again",
	                         "Blank Field", 
	                         JOptionPane.ERROR_MESSAGE);
	                        }
	                    else
	                        {
	                      // perform exit operation
	                      boolean result = Garage.myCarLot.carExit(ticketNumber);    
	                      
	                        if(!result)   // check outcome and report results
	                        {
	                          JOptionPane.showMessageDialog((Component) buttonPanel, 
	                          "Invalid Ticket " +
	                          "Please try again.","Invalid Operation", 
	                          JOptionPane.ERROR_MESSAGE);
	                        }
	                        else
	                        {	                                           
	                           // calculate fees
	                        	
	                        	int another = JOptionPane.showConfirmDialog((Component) 
	                          buttonPanel,"Amount Due",
	                          null, JOptionPane.YES_NO_OPTION);
	                          //enter payment
	                        	
	                        	
	                          carPlateField.setText("");    // reset input field						
	                          ticketNumberField.setText("");
	                            
	                        }					
	                    }
	                }
	        });
			return carExitScreen1;
}
}
