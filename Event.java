import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Event {
	
	int cashAmount;
	String currency;
	Date dateOfEvent;
	String dateString;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

	/*
		Event Constructor for creating an Event object
	*/
	public Event(int amountIn, String ccyIn, String dateTimeIn) throws ParseException {
	
		cashAmount = amountIn;
		currency = ccyIn;
		dateString = dateTimeIn;
		dateOfEvent = simpleDateFormat.parse(dateTimeIn);
		
	}

	/*
		getter method for the cashAmount variable
	*/
	public int getAmount() {
		return cashAmount;
	}
	
	/*
		getter method for the currency variable
	*/
	public String getCurrency() {
		return currency;
	}
	
	/*
		getter method for the dateOfEvent variable
	*/
	public Date getDate() {
		return dateOfEvent;
	}
	
	/*
		getter method for the dateString variable
	*/
	public String getDateString() {
		return dateString;
	}
	
	/*
		toString method for printing out the fields for the given Event object
	*/
	public String toString() {
		return "Cash amount: " + cashAmount +
					"\nCurrency: " + currency +
					"\nDate: " + simpleDateFormat.format(dateOfEvent) +
					"\n";
	}


}