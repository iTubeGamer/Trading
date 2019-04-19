package de.maxkroner.stockportfoliomanager.stockportfoliomanager;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	
	private String isin;
	private String wkn;
	private String xetraMnemonic;
	private List<Exchange> exchanges = new ArrayList<>();
	
	
	public Stock(String isin, String wkn, String xetraMnemonic) {
		super();
		this.isin = isin;
		this.wkn = wkn;
		this.xetraMnemonic = xetraMnemonic;
	}
	
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getWkn() {
		return wkn;
	}
	public void setWkn(String wkn) {
		this.wkn = wkn;
	}
	public String getXetraMnemonic() {
		return xetraMnemonic;
	}
	public void setXetraMnemonic(String xetraMnemonic) {
		this.xetraMnemonic = xetraMnemonic;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}
	
	public void addExchange(Exchange exchange) {
		exchanges.add(exchange);
	}

}
