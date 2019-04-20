package de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.exception;

public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = -3167174975425036256L;
	
	public StockNotFoundException() {
		super();
	}
	
	public StockNotFoundException(String message) {
		super(message);
	}

}
