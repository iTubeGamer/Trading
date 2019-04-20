package de.maxkroner.stockportfoliomanager.stockportfoliomanager.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.StockRepository;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api.StockService;

@RestController
@CrossOrigin
public class StockController {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/stocks")
	public List<Stock> getStocksWithSearchString(@RequestParam(value="search", required=false) String search){
		if(search == null) {
			
			return stockRepository.findAll();
		}
		return stockService.getStocksFromSearchString(search);
	}
	
    @PostMapping("/stock")
    void addUser(@RequestBody Stock stock) {
        stockRepository.save(stock);
    }

}
