package de.maxkroner.stockportfoliomanager.stockportfoliomanager;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Exchange;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.ExchangeRepository;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository.StockRepository;

@SpringBootApplication
public class StockportfoliomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockportfoliomanagerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(StockRepository stockRepository, ExchangeRepository exchangeRepository) {
		return (args) -> {
			//save stocks
			Stock apple = new Stock("Apple Inc", "123456789012", "123456", "APC");
			Exchange frankfurt = new Exchange("Frankfurt", ".F", "Germany");
			Exchange xetra = new Exchange("XETRA", ".DE", "Germany");
			apple.addExchange(frankfurt);
			apple.addExchange(xetra);
			stockRepository.save(apple);

			//find alls stocks
	    	List<Stock> stocks = stockRepository.findByWkn("123456");
	    	if(stocks != null) {
	    		for (Stock stock : stocks) {
	    			System.out.println(stock);
	    		}
	    	} else {
	    		System.out.println("list is null!");
	    	}
		};
		
	}
}
