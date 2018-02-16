package de.maxkroner.trading.onada.main;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;

import de.maxkroner.trading.onada.values.Config;

public class AccountInfo {

	public static void main(String[] args) {
		Context ctx = new Context(
				Config.URL,
				Config.TOKEN);
		try {
			AccountSummary summary = ctx.account.summary(Config.ACCOUNTID).getAccount();
			System.out.println(summary);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}