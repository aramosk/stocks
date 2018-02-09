package com.alfredo.assignment.stocksApp.repo;

import com.alfredo.assignment.stocksApp.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring data repository class for Stocks.
 *
 * @author aramosk
 */
public interface StockDao extends PagingAndSortingRepository<Stock, Long> {
}
