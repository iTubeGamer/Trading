package de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	List<Stock> findByWkn(String wkn);
	
	List<Stock> findByIsin(String isin);
	
	List<Stock> findByXetraMnemonic(String xetraMnemonic);
	
	List<Stock> findByNameContaining(String name);
}
