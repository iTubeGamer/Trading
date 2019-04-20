package de.maxkroner.stockportfoliomanager.stockportfoliomanager.main;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.data.Stock;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockUpdateException;
import de.maxkroner.stockportfoliomanager.stockportfoliomanager.service.api.StockService;

@ComponentScan(basePackages = "de.maxkroner.stockportfoliomanager")
public class App 
{
	@Inject
	private StockService stockService;
	
    public static void main( String[] args ) throws StockUpdateException
    {
    	
    	ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    	
    	App app = context.getBean(App.class);
    	app.start(args);
	
    }
    
    private void start(String[] args) throws StockUpdateException {
    	stockService.updateAvailableStocks();

    	
    	List<Stock> stocks = stockService.getAllStocks();
    	for (Stock stock : stocks) {
			System.out.println(stock);
		}
    }
    
    

}
