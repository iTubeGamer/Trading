package de.maxkroner.stockportfoliomanager.stockportfoliomanager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "EXCHANGES")
public class Exchange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "EXCHANGE_NAME", unique=true, nullable=false)
	private String exchangeName;
	
	@Column(name = "EXCHANGE_SUFFIX", unique=true, nullable=false)
	private String exchangeSuffix;
	
	@Column(name = "COUNTRY", unique=false, nullable=false)
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "exchanges")
	private Set<Stock> tradableStocks = new HashSet<>();
		
	public Exchange() {
		super();
	}

	public Exchange(String exchangeName, String exchangeSuffix, String country) {
		super();
		this.exchangeName = exchangeName;
		this.exchangeSuffix = exchangeSuffix;
		this.country = country;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public String getExchangeSuffix() {
		return exchangeSuffix;
	}
	public void setExchangeSuffix(String exchangeSuffix) {
		this.exchangeSuffix = exchangeSuffix;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
