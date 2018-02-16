package de.maxkroner.trading.onada.main;

import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.primitives.Instrument;

import de.maxkroner.trading.onada.values.Config;

public class Instruments {
	
	public static void main(String[] args) {
		Context ctx = new Context(Config.URL, Config.TOKEN);
	    AccountID accountId = Config.ACCOUNTID;
	    
		try {
			List<Instrument> instruments = ctx.account.instruments(accountId).getInstruments();
			for (Instrument instrument : instruments) {
				System.out.println(instrument.getDisplayName() + " | " + instrument.getName() + " | " + instrument.getType());
			}
		} catch (RequestException | ExecuteException e) {
			e.printStackTrace();
		}
		
	}

}
