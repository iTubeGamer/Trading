package de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api;

import java.util.List;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.exception.StockNotFoundException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.exception.StockUpdateException;

/**
 * Service interface to access stocks.
 * 
 * @author Max Kroner
 *
 */

public interface StockService {
	
	/**
	 * Finds stock if valid ISIN, WKN or Mnemonic is recognized;
	 * 
	 * @param search ISIN, WKN or Mnemonic
	 * @return stock
	 * @throws StockNotFoundException
	 */
	List<Stock> getStocksFromSearchString(String search);
	
	
	/**
	 * Updates the available stock list from XETRA.
	 * 
	 * @return Number of stocks added.
	 * @throws StockUpdateException
	 */
	int updateAvailableStocks() throws StockUpdateException;
	

}
