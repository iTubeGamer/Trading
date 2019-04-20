package de.maxkroner.stockportfoliomanager.stockportfoliomanager.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "STOCKS", indexes = {@Index(name =  "idIndex", columnList = "ID"), @Index(name =  "wknIndex", columnList = "WKN")})
public class Stock {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@Column(name = "ISIN", unique = true, nullable = false)
	private String isin;
	
	@Column(name = "WKN", unique = true, nullable = false)
	private String wkn;
	
	@Column(name = "XETRA_MNEMONIC", unique = true, nullable = false)
	private String xetraMnemonic;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(name = "EXCHANGE_STOCK", joinColumns={@JoinColumn(referencedColumnName="ID")}, 
    inverseJoinColumns={@JoinColumn(referencedColumnName="ID")})
	private Set<Exchange> exchanges = new HashSet<>();
	
	public Stock() {
		super();
	}

	public Stock(String name, String isin, String wkn, String xetraMnemonic) {
		super();
		this.name = name;
		this.isin = isin;
		this.wkn = wkn;
		this.xetraMnemonic = xetraMnemonic;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getWkn() {
		return wkn;
	}
	public void setWkn(String wkn) {
		this.wkn = wkn;
	}
	public String getXetraMnemonic() {
		return xetraMnemonic;
	}
	public void setXetraMnemonic(String xetraMnemonic) {
		this.xetraMnemonic = xetraMnemonic;
	}

	public Set<Exchange> getExchanges() {
		return exchanges;
	}
	
	public void addExchange(Exchange exchange) {
		exchanges.add(exchange);
	}
	
	public boolean removeExchange(Exchange exchange) {
		return exchanges.remove(exchange);
	}
	
	@Override
	public String toString() {
		String exchangesString = exchanges.stream().map(e -> e.getExchangeName()).collect(Collectors.joining(","));
		return String.format("%s (ISIN: %s, WKN: %s, Mnemonic: %s, Exchanges: [%s])", name, isin, wkn, xetraMnemonic, exchangesString);
	}

}
