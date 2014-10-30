package cs414.a4.kaushikb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class CarLot {
	private Vector carOwner;
    private Vector parkingSpace;
    private String dataFile;
    private int maxLotSize;
    
    public CarLot(int maxSize, String fileName)   // constructor
    {
        carOwner = new Vector();
        parkingSpace = new Vector();
        maxLotSize = maxSize;
        dataFile = fileName;
        loadData();
    }

    public String getDataFileName()
    {
        return dataFile;
    }

    public int getMaxSize()
    {
        return maxLotSize;
    }

    public int carCount()
    {
        return parkingSpace.size();
    }

    // Input: Registered plate number
    // Output: Space number in parking lot
    public String findSpaceLocation(String plateNum)
    {
        String currentSpace = "";
        String returnVal = "";

        for(int i = 0; i < parkingSpace.size(); i++)
        {
            currentSpace = (String)parkingSpace.elementAt(i);

            if(plateNum.equals(currentSpace))
            {
                returnVal = Integer.toString(i);
                break;
            }           
        }

        return returnVal;
    }

    public int loadData()   // Loads data from file (maintain persistence upon close)
    {
        // each row in the data file represents a registered car
        // plateNum|currentlyParked

        FileReader file;
        BufferedReader buffer;
        StringTokenizer tokens;
        String currentLine = "";
        String plateNum = "";
        String currentlyParked = "";
        
        try
        {
            file = new FileReader(dataFile);
            buffer = new BufferedReader(file);

            while((currentLine = buffer.readLine()) != null)     // read and parse each line in the file
            {
                int returnVal = 0;
                tokens = new StringTokenizer(currentLine, "|");
                plateNum = tokens.nextToken();
                currentlyParked = tokens.nextToken();

                carOwner.addElement(plateNum);  // load all registered drivers

                if(currentlyParked.equals("Y")) // load car into parking space if status is "Y"
                {
                   if(parkingSpace.size() < maxLotSize)
                   {
                     parkingSpace.addElement(plateNum);
                   }
                   else
                   {
                        returnVal = -1;
                   }
                }
            }   
        }
        catch(FileNotFoundException f)
        {
            return -1;
        }
        catch(IOException io)
        {
            return -1;
        }       
        
        return 0;
    }

    public int saveData()   // Saves status data upon request to data file
	{
		FileWriter writer = null;
		PrintWriter printer = null;
		String currentRecord = "";
		String carPlate = "";
		String parkedPlates = "";
		String currentlyParked = "";

		try
		{
			writer = new FileWriter(dataFile);
			printer = new PrintWriter(writer);

			for(int i = 0; i < carOwner.size(); i++) // build data record by parsing Vectors
			{
			  carPlate = (String)carOwner.elementAt(i);
			  currentlyParked = "N";

			  for(int j = 0; j < parkingSpace.size(); j++)
			  {
			    parkedPlates = (String)parkingSpace.elementAt(j);

			    if(parkedPlates.equals(carPlate))
			    {
			      currentlyParked = "Y";
			      break;
			    }
			  }

			  currentRecord = carPlate + "|" + currentlyParked;
			  printer.println(currentRecord);    // output record to file
			}

			// close output streams
			writer.close();
			printer.close();
		}
		catch(IOException io)
		{
		  return -1;
		}	

		return 0;
	}

	public boolean carEnter(String licenseNum)
	{
		String parkedCar = "";
		boolean alreadyHere = false;
		
		// Check Spaces to see if car is already parked
		for(int i = 0; i < parkingSpace.size(); i++)
		{
			parkedCar = (String)parkingSpace.elementAt(i);

			if(parkedCar.equals(licenseNum))
			{
				alreadyHere = true;
			}			
		}
		
		// car is not already parked
		if(!alreadyHere)
		{
			// space is still available
			if(!lotFull())
			{
				parkingSpace.addElement(licenseNum);
				return true;
            }
			// space not available
			else
			{
				return false;
			}
		}
		// car already parked in lot
		else
		{
			return false;
		}
	}

	public boolean carExit(String licenseNum)
	{
		boolean returnVal = false; //default return value
		String parkedCar = "";

		// searching for car in Spaces
		for(int i = 0; i < parkingSpace.size(); i++)
		{
			parkedCar = (String)parkingSpace.elementAt(i);

			// car found
			if(parkedCar.equals(licenseNum))
			{
				parkingSpace.removeElementAt(i);
				returnVal = true;
				break;
			}			
		}

		return returnVal;
	}

	public boolean lotFull()
	{
		// compare Spaces occupied to max lot size
		if(parkingSpace.size() == maxLotSize)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
