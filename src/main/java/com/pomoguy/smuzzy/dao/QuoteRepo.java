package com.pomoguy.smuzzy.dao;

import com.pomoguy.smuzzy.model.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuoteRepo extends CrudRepository<Quote, Integer> {
    List<Quote> findByQuoteText(String quoteText);
}
