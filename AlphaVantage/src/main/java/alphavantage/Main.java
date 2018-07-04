package alphavantage;

import java.util.concurrent.TimeUnit;

import org.patriques.AlphaVantageConnector;
import org.patriques.ForeignExchange;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.exchange.CurrencyExchange;
import org.patriques.output.timeseries.Daily;

public class Main {
	private static AlphaVantageConnector apiConnector = new AlphaVantageConnector(Config.API_KEY, Config.TIMEOUT);

	public static void main(String[] args) {
		//printStopInPercentage("GOOG", 865);
		//printStopInPercentage("ATVI", 51);
		//printStopInPercentage("NVDA", 185);
		//printStopInPercentage("FIS", 83);
		printStopInPercentage("MSFT", 78);
		printStopInPercentage("AMZN", 1230);
		printStopInPercentage("ADBE", 195);
		printStopInPercentage("TTD", 71);
		printStopInPercentage("MU", 37);
		printStopInPercentage("GDDY", 52);
		printStopInPercentage("NFLX", 324);
	}
	
	
	public static void printStopInPercentage(String symbol, double stopInEuros) {
		TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
		ForeignExchange foreignExchange = new ForeignExchange(apiConnector);
		
		
		try {
			CurrencyExchange currencyExchange = foreignExchange.currencyExchangeRate("EUR", "USD");
			float exchangeRate = currencyExchange.getData().getExchangeRate();
			double stop = Math.round(stopInEuros * exchangeRate * 100) / 100;
			
			Daily response = stockTimeSeries.daily(symbol, OutputSize.COMPACT);
			
			double price = response.getStockData().get(0).getClose(); 
			
			double percentage = Math.round((1 - (stop / price)) * 10000) / 100;
			
			System.out.println("Symbol: " + symbol);
			System.out.println("Price: " + price);
			System.out.println("Stop: " + stop);
			System.out.println("Perc: " + percentage);
			System.out.println("------------------");
			
			TimeUnit.SECONDS.sleep(3);			
		}catch (AlphaVantageException | InterruptedException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				
			}	
		}
		
		
		
	}


}
