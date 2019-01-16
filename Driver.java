import java.text.ParseException;
import java.util.ArrayList;

public class Driver {


	public static void main(String[] args) throws ParseException {
		
		CashFlow testCashFlowObj = new CashFlow();
		ArrayList<Event> cashFlowsList;
		
		/* day - month - year */
		testCashFlowObj.addCashFlow(7000, "USD", "01-06-2018");
		testCashFlowObj.addCashFlow(557, "Yen", "01-06-2018");
		testCashFlowObj.addCashFlow(2222, "Euro", "08-16-1996");
		testCashFlowObj.addCashFlow(-19, "Pound", "05-05-3000");
		
		cashFlowsList = testCashFlowObj.getEventsList();
		
		for(Event ev : cashFlowsList) {
			assert ev != null : "FoundNullEventObject";
		}
		
		System.out.println("============ Passed Test One ============");
		
		int testBalanceOne = testCashFlowObj.getBalance("Pound", "05-05-3000");
		int testBalanceTwo = testCashFlowObj.getBalance("USD", "01-06-2018");
		int testBalanceThree = testCashFlowObj.getBalance("Yen", "01-06-2018");
		int testBalanceFour = testCashFlowObj.getBalance("Euro", "08-16-1996");
		
		assert testBalanceOne == -19 : "TestBalanceOneFailed";
		assert testBalanceTwo == 7000 : "TestBalanceTwoFailed";
		assert testBalanceThree == 557 : "TestBalanceThreeFailed";
		assert testBalanceFour == 2222 : "TestBalanceFourFailed";
		
		System.out.println("============ Passed Test Two ============");
		
		cashFlowsList = testCashFlowObj.getCashFlows("01-06-2000", "05-05-3000");
		
		assert cashFlowsList.size() == 3 : "CashFlowsListSizeFailed";
		assert cashFlowsList.get(1).getAmount() == 557 : "CashFlowsListGetAmountFailed";
		assert cashFlowsList.get(2).getCurrency().equals("Pound") : "CashFlowsListGetCurrencyFailed";
		
		System.out.println("============ Passed Test Three ============");
		
		int testPortfolioValuesOne = testCashFlowObj.getPortfolioValueInUSD(.88, "05-04-3000");
		int testPortfolioValuesTwo = testCashFlowObj.getPortfolioValueInUSD(1.99, "01-05-2000");
		
		assert testPortfolioValuesOne == 9445 : "TestPortfolioValuesFailed";
		assert testPortfolioValuesTwo == 11421 : "TestPortfolioValuesFailed";
		
		System.out.println("============ Passed Test Four ============");	
	}


}

