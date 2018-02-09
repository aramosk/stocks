package com.alfredo.assignment.stocksApp.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO class for partial updates of stock properties
 *
 * @author aramosk
 */
public class StockDTO {

    @DecimalMin(value = "0.00")
    @NotNull
    private BigDecimal currentPrice;

    public StockDTO() {

    }

    public StockDTO(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
