package de.maxkroner.trading.onada.main;

import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;


import de.maxkroner.trading.onada.values.Config;

public class CandlesRequestTest {
	
	 public static void main(String[] args) {
	        Context ctx = new Context(Config.URL, Config.TOKEN);
	        AccountID accountId = Config.ACCOUNTID;
	        InstrumentName instrument = new InstrumentName("EUR_USD");;
	        DateTime from = new DateTime("2018-01-01T00:00:00Z");
	        DateTime to = new DateTime("2018-01-31T23:59:59Z");
	        CandlestickGranularity granularity = CandlestickGranularity.H1;

	        // Poll for prices
	        try {
	        	InstrumentCandlesRequest request = new InstrumentCandlesRequest(instrument)
	        			.setFrom(from)
	        			.setTo(to)
	        			.setGranularity(granularity);
	        	
	        	InstrumentCandlesResponse response = ctx.instrument.candles(request);
	        	
	        	List<Candlestick> candles = response.getCandles();
	        	
	        	for (Candlestick candlestick : candles) {
					System.out.println(candlestick.getTime() + " | " + candlestick.getMid());
				}

	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

}
