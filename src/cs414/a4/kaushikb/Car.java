package cs414.a4.kaushikb;

public class Car {
    private String type;
    private String ownerName;
    private String plateNo;
    
	public Car(String type, String ownerName, String plateNo)	
	{
         		this.type = type;
         		this.ownerName = ownerName;
        	              this.plateNo = plateNo;	
	}
	
	public Car() {
	}
	
	public void setType(String type) {
	this.type = type;
	}
	public String getownerName() {
	return ownerName;
	}
	
	public String getPlateNo() {
	return plateNo;
	}
	
}
