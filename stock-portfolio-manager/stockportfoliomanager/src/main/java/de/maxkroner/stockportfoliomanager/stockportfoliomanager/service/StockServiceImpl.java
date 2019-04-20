package de.maxkroner.stockportfoliomanager.stockportfoliomanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.StockRepository;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.exception.StockUpdateException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;


	
	@Override
	public List<Stock> getStocksFromSearchString(String search) {
		if(search.length() == 6) {
			List<Stock> list = stockRepository.findByWkn(search);
			if(!list.isEmpty()) return list;
		} else if (search.length() == 12) {
			List<Stock> list = stockRepository.findByIsin(search);
			if(!list.isEmpty()) return list;
		} else {
			List<Stock> list = stockRepository.findByXetraMnemonic(search);
			if(!list.isEmpty()) return list;
		}
		
		List<Stock> list = stockRepository.findByNameContaining(search);
		
		return list;
	}
	

	
	@Override
	public int updateAvailableStocks() throws StockUpdateException {

		/*
		try {
			Stock stockByWkn = stockRepository.getStockByWkn("123456");
			return 0;
		} catch (StockNotFoundException e) {
			Stock apple = new Stock("Apple Inc", "12345678901", "123456", "APC");
			stockRepository.saveStock(apple);
			return 1;
		}
		*/
		
		return 0;

	}
	
	
	

}
