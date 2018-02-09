package com.alfredo.assignment.stocksApp.services;

import com.alfredo.assignment.stocksApp.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface for the Stock Service.
 *
 * @author aramosk
 */
public interface StockService {

    /**
     * Retrieve a list of stocks.
     *
     * @return a list of stocks
     */
    List<Stock> getAllStocks();

    /**
     * Retrieve a list of stocks by pageable.
     *
     * @return a page with a list of stocks
     */
    Page<Stock> getStocksByPage(Pageable pageable);

    /**
     * Retrieve a stock by id.
     *
     * @param id a stock identifier
     * @return a stock
     */
    Stock getStock(Long id);

    /**
     * Create a new stock.
     *
     * @param stock a stock to create
     * @return a created stock
     */
    Stock createStock(Stock stock);

    /**
     * Update an existing stock.
     *
     * @param stock a stock to update
     * @return an updated stock
     */
    Stock updateStock(Stock stock);

    /**
     * Delete an existing stock.
     *
     * @param id an stock identifier
     */
    void deleteStock(Long id);
}
