package com.alfredo.assignment.stocksApp.services;

import com.alfredo.assignment.stocksApp.model.Stock;
import com.alfredo.assignment.stocksApp.repo.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation class of Stock Service.
 *
 * @author aramosk
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockDao.findAll();
    }

    @Override
    public Page<Stock> getStocksByPage(Pageable pageable) {
        return stockDao.findAll(pageable);
    }

    @Override
    public Stock getStock(Long id) {
        return stockDao.findOne(id);
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockDao.save(stock);
    }

    @Override
    public Stock updateStock(Stock stock) {
        return stockDao.save(stock);
    }

    @Override
    public void deleteStock(Long id) {
        stockDao.delete(id);
    }

}
