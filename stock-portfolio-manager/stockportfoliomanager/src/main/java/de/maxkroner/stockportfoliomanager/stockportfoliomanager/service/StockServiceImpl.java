package de.maxkroner.stockportfoliomanager.stockportfoliomanager.service;

import java.util.List;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.data.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.data.StockDAO;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockNotFoundException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockUpdateException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api.StockService;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
	
	@Inject
	private StockDAO stockDAO;

	@Override
	public List<Stock> getAllStocks() {
		return stockDAO.getAllStocks();
	}

	@Override
	public Stock getStockById(Long id) throws StockNotFoundException {
		
		return stockDAO.getStockById(id);
	}

	@Override
	public Stock getStockFromSearchString(String search) throws StockNotFoundException {
		if(search.length() == 12) {
			return stockDAO.getStockByIsin(search);
		} else if (search.length() == 6) {
			return stockDAO.getStockByWkn(search);
		} else {
			return stockDAO.getStockByMnemonic(search);
		}
	}

	@Override
	public int updateAvailableStocks() throws StockUpdateException {

		try {
			Stock stockByWkn = stockDAO.getStockByWkn("123456");
			return 0;
		} catch (StockNotFoundException e) {
			Stock apple = new Stock("Apple Inc", "12345678901", "123456", "APC");
			stockDAO.saveStock(apple);
			return 1;
		}

	}
	
	

}
