package com.payconiq.assignment.stocksApp.repo;

import com.payconiq.assignment.stocksApp.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring data repository class for Stocks.
 *
 * @author aramosk
 */
public interface StockDao extends PagingAndSortingRepository<Stock, Long> {
}
