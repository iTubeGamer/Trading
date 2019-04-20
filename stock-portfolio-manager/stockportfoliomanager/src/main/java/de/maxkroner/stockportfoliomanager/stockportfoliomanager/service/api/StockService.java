package de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api;

import java.util.List;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.data.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockNotFoundException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockUpdateException;

/**
 * Service interface to access stocks.
 * 
 * @author Max Kroner
 *
 */
public interface StockService {
	
	/**
	 * Gets all available stocks.
	 * 
	 * @return list of stocks
	 */
	List<Stock> getAllStocks();
	
	
	/**
	 * Finds a stock by id.
	 * 
	 * @param id
	 * @return stock
	 * @throws StockNotFoundException
	 */
	Stock getStockById(Long id)  throws StockNotFoundException;
	
	/**
	 * Finds stock if valid ISIN, WKN or Mnemonic is recognized;
	 * 
	 * @param search ISIN, WKN or Mnemonic
	 * @return stock
	 * @throws StockNotFoundException
	 */
	Stock getStockFromSearchString(String search) throws StockNotFoundException;
	
	
	/**
	 * Updates the available stock list from XETRA.
	 * 
	 * @return Number of stocks added.
	 * @throws StockUpdateException
	 */
	int updateAvailableStocks() throws StockUpdateException;
	

}
