package de.maxkroner.stockportfoliomanager.stockportfoliomanager.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.exception.StockNotFoundException;

@Repository
public class StockDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * saves stock in db
	 * 
	 * @param stock
	 * @return persisted stock
	 */
	public Stock saveStock(Stock stock) {
		entityManager.getTransaction().begin();
		entityManager.persist(stock);
		entityManager.getTransaction().commit();
		return stock;
	}
	
	/**
	 * loads stock by id from db
	 * 
	 * @param id
	 * @return stock by id
	 * @throws StockNotFoundException
	 */
	public Stock getStockById(Long id) throws StockNotFoundException {
		Stock stock = entityManager.find(Stock.class, id);
		if(stock != null) {
			return stock;
		}
		throw new StockNotFoundException("No stock with id " + id + " could be found.");
	}
	
	/**
	 * loads stock by isin from db
	 * 
	 * @param isin
	 * @return stock by isin
	 * @throws StockNotFoundException
	 */
	public Stock getStockByIsin(String isin) throws StockNotFoundException {
		Query query = entityManager.createQuery("select stock from Stock as stock where stock.isin = :isin", Stock.class);
		query.setParameter("isin", isin);
		List<Stock> results= query.getResultList();
		
		if(!results.isEmpty()) {
			return results.get(0);
		}
		throw new StockNotFoundException("No stock with ISIN " + isin + " could be found.");
	}
	
	
	/**
	 * loads stock by wkn from db
	 * 
	 * @param wkn
	 * @return stock by wkn
	 * @throws StockNotFoundException
	 */
	public Stock getStockByWkn(String wkn) throws StockNotFoundException {
		Query query = entityManager.createQuery("select stock from Stock as stock where stock.wkn = :wkn", Stock.class);
		query.setParameter("wkn", wkn);
		List<Stock> results= query.getResultList();
		
		if(!results.isEmpty()) {
			return results.get(0);
		}
		throw new StockNotFoundException("No stock with WKN " + wkn + " could be found.");
	}
	
	/**
	 * loads stock by mnemonic from db
	 * 
	 * @param mnemonic
	 * @return stock by mnemonic
	 * @throws StockNotFoundException
	 */
	public Stock getStockByMnemonic(String mnemonic) throws StockNotFoundException {
		Query query = entityManager.createQuery("select stock from Stock as stock where stock.xetraMnemonic = :mnemonic", Stock.class);
		query.setParameter("mnemonic", mnemonic);
		List<Stock> results= query.getResultList();
		
		if(!results.isEmpty()) {
			return results.get(0);
		}
		throw new StockNotFoundException("No stock with symbol " + mnemonic + " could be found.");
	}
	
	/**
	 * loads all stocks
	 * @return list of stocks
	 */
	public List<Stock> getAllStocks(){
		return entityManager.createQuery("select stock from Stock as stock", Stock.class).getResultList();
	}

}
