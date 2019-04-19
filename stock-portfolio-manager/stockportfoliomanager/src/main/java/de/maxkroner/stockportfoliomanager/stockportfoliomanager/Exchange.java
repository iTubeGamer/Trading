package de.maxkroner.stockportfoliomanager.stockportfoliomanager;

public class Exchange {
	
	private String exchangeName;
	private String exchangeSuffix;
	private String country;
	
	public Exchange(String exchangeName, String exchangeSuffix, String country) {
		super();
		this.exchangeName = exchangeName;
		this.exchangeSuffix = exchangeSuffix;
		this.country = country;
	}
	
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public String getExchangeSuffix() {
		return exchangeSuffix;
	}
	public void setExchangeSuffix(String exchangeSuffix) {
		this.exchangeSuffix = exchangeSuffix;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
