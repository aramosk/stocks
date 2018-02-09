package com.alfredo.assignment.stocksApp.controller;

import com.alfredo.assignment.stocksApp.model.StockDTO;
import com.alfredo.assignment.stocksApp.services.StockService;
import com.alfredo.assignment.stocksApp.model.Stock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for the RESTful stocks api.
 *
 * @author aramosk
 */
@RestController
@RequestMapping(value = "/api")
@Api(value="stocks", description="REST operations over stocks")
public class StocksController {

    public static final Logger LOG = LoggerFactory.getLogger(StocksController.class);

    @Autowired
    private StockService stockService;

    /*
        GET /api/stocks (get a list of stocks)
     */
    @ApiOperation(value = "View a list of available stocks", response = Iterable.class)
    @RequestMapping("/stocks")
    public List<Stock> getStocks() {
        return stockService.getAllStocks();
    }

    @RequestMapping("/stocks/{page}/{size}")
    public Page<Stock> getStocksByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return stockService.getStocksByPage(new PageRequest(page, size, Sort.Direction.DESC,"name"));
    }

    /*
        GET /api/stocks/{id} (get one stock from the list)
     */
    @RequestMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable Long id) {

        LOG.debug("Fetching stock with id: {}", id);

        Stock stock = stockService.getStock(id);

        if (stock == null) {
            return ResponseEntity.notFound().build();
        }

        LOG.info("Stock retrieved: {}", stock);

        return ResponseEntity.ok().body(stock);
    }

    /*
        POST /api/stocks (create a stock)
     */
    @PostMapping("/stocks")
    public Stock createStock(@Valid @RequestBody Stock stock) {

        LOG.debug("Creating new stock: {}", stock);

        return stockService.createStock(stock);
    }

    /*
        PUT /api/stocks/{id} (update the price of a single stock)
     */
    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updatePrice(@PathVariable Long id, @Valid @RequestBody StockDTO stockDTO) {

        Stock updatedStock = stockService.getStock(id);

        if (updatedStock == null) {
            return ResponseEntity.notFound().build();
        }

        LOG.debug("The price of {} will be updated with the new price: {}", updatedStock, stockDTO.getCurrentPrice());

        updatedStock.setCurrentPrice(stockDTO.getCurrentPrice());

        updatedStock = stockService.updateStock(updatedStock);

        LOG.info("Stock with the price updated: {}", updatedStock);

        return ResponseEntity.ok(updatedStock);
    }

    /*
        DELETE /api/stocks/{id} (delete a single stock)
     */
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Stock> deleteStock(@PathVariable Long id) {

        Stock stockToDelete = stockService.getStock(id);

        if (stockToDelete == null) {
            return ResponseEntity.notFound().build();
        }

        LOG.debug("Deleting stock: {}", stockToDelete);

        stockService.deleteStock(id);

        return ResponseEntity.ok().build();
    }

}
