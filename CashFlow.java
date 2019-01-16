/*
	Author: Tony Famighetti
	
	Program simulating cash flow in a bank.
	
	Methods include:
		addCashFlow(amount, ccy, datetime)
		getBalance(ccy, datetime)
		getCashFlows(startDate, endDate)
		getPortfolioValueInUSD(rates, datetime)

	Assumptions I made:
		- Currencies are unique. Usually unique elements would call for using a Set but I chose to
		use an ArrayList instead for indexing and lookup. 
		- For the getPortfolioValueInUSD I assumed the rate was universal for all currencies (which we know
		is not the case) so that the same rate was applied to all the currencies, excluding USD.
		
	Sources:
		https://www.guru99.com/java-date.html

*/

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashFlow {
		
	ArrayList<Event> eventsList = new ArrayList<Event>();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
		
	/*
		Defining classes constructor though default would be sufficient
	*/
	public CashFlow() {
		
	}
	
	/*
		This method is to add some sort of cashflow (positive or negative) that will happen at a given point in time
		Here, we create a new event every time there is a cashflow "transaction"
	*/
	public void addCashFlow(int amount, String ccy, String datetime) throws ParseException {
		Event newEvent = new Event(amount, ccy, datetime);
	
		eventsList.add(newEvent);
	}
	
	/*
		This method is used to get the balance of a given currency at a given point in time
		Loops through the current events list and returns the balance for the event at the given point in time
	*/
	public int getBalance(String ccy, String datetime) {
		int returnBalance = 0;
		
		for(Event ev : eventsList) {
			if(ev.getCurrency().equals(ccy) && ev.getDateString().equals(datetime)) {
				returnBalance = ev.getAmount();
			}
		}
		
		return returnBalance;
	}
	
	/* 	
		This method is used to get a list of all currently defined cashflow events
		> 0 means we're after the start date ... < 0 means we're before the end date  	
		but we can also have an event that is on the start date or end date
	*/
	public ArrayList<Event> getCashFlows(String startDate, String endDate) throws ParseException {
			ArrayList<Event> retList = new ArrayList<Event>();
			
			Date startDateFormatted = simpleDateFormat.parse(startDate);
			Date endDateFormatted = simpleDateFormat.parse(endDate);
			
			for(Event ev : eventsList) {
				if(( ev.getDate().compareTo(startDateFormatted) > 0 && ev.getDate().compareTo(endDateFormatted) < 0 ) ||
					(ev.getDate().compareTo(startDateFormatted) == 0 || ev.getDate().compareTo(endDateFormatted) == 0) ) {
						retList.add(ev);
				}					
			}
			
			return retList;
	}
	
	/*
		This method is used to calculate the total balance in USD at a given point in time
		Calculated the conversion rates for all other currencies that weren't USD and then added
		the USD at the end
	*/
	public int getPortfolioValueInUSD(double rates, String datetime) throws ParseException {
		
		Date datetimeFormatted = simpleDateFormat.parse(datetime);
		
		int runningTotal = 0;
		int usdAmount = 0;
		
		for(Event ev : eventsList) {
			if( !ev.getCurrency().equals("USD") ) {
				if( ev.getDate().compareTo(datetimeFormatted) < 0 || ev.getDate().compareTo(datetimeFormatted) == 0 ) {
					runningTotal += ev.getAmount();
				}
			} else {
				usdAmount = ev.getAmount();
			}
		}
		
		runningTotal *= rates;
		
		runningTotal += usdAmount;	
		
		return runningTotal;
		
	}
	
	/*
		getter method for the eventsList arraylist field
	*/
	public ArrayList<Event> getEventsList() {
		return eventsList;
	}
	

}
