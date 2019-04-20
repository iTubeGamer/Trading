package de.maxkroner.stockportfoliomanager.stockportfoliomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.maxkroner.stockportfoliomanager.stockportfoliomanager.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>{}
