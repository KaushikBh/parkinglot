package cs414.a4.kaushikb;

public class PaymentSystem {
	int totalhour;
	int rate;
	
	public PaymentSystem(int hour, int rate)	
	{
         		this.totalhour = hour;
         		this.rate = rate;
        	          
	}
	
	void calculatePayment(){
		int due = totalhour * rate;
	}
	
}
