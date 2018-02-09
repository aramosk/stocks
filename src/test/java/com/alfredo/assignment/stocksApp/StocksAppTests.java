package com.alfredo.assignment.stocksApp;

import com.alfredo.assignment.stocksApp.controller.StocksController;
import com.alfredo.assignment.stocksApp.model.StockDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alfredo.assignment.stocksApp.model.Stock;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test class for testing stock RESTful api.
 *
 * @author aramosk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class StocksAppTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StocksController stocksCotroller;

    @Test
    public void controllerInitializedCorrectly() {
        Assertions.assertThat(stocksCotroller).isNotNull();
    }

    @Test
    public void getSingleStockTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("facebook")))
                .andExpect(jsonPath("currentPrice", is(5000.75)));

        //Stock not found
        mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getListStockTest() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("facebook")))
                .andExpect(jsonPath("$[0].currentPrice", is(5000.75)))
                .andExpect(jsonPath("$[1].name", is("yahoo")))
                .andExpect(jsonPath("$[1].currentPrice", is(1500.85)))
                .andExpect(jsonPath("$[2].name", is("google")))
                .andExpect(jsonPath("$[2].currentPrice", is(2000.95)))
                .andExpect(jsonPath("$[3].name", is("amazon")))
                .andExpect(jsonPath("$[3].currentPrice", is(2500.60)))
                .andExpect(jsonPath("$[4].name", is("uber")))
                .andExpect(jsonPath("$[4].currentPrice", is(3000.50)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void createStockTest() throws Exception {

        final String expectedName = "airbnb";
        final BigDecimal expectedPrice = BigDecimal.valueOf(4500.00);

        //create a new stock
        mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                .content(toJson(new Stock(expectedName, expectedPrice)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(expectedName)))
                .andExpect(jsonPath("currentPrice", is(expectedPrice.doubleValue())));

        Stock[] stocksToTest = {
                null, // no content
                new Stock(expectedName, BigDecimal.valueOf(-4500.00)), //Negative price amount
                new Stock("", expectedPrice) // Empty stock name
        };

        for (Stock stock : stocksToTest) {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                    .content(toJson(stock))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

    }

    @Test
    public void updateStockTest() throws Exception {

        final String stockName = "facebook";
        final BigDecimal stockPrice = BigDecimal.valueOf(10500.75);

        final BigDecimal newPrice = BigDecimal.valueOf(7500.75);

        //Create a new stock
        mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                .content(toJson(new Stock(stockName, stockPrice)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(stockName)))
                .andExpect(jsonPath("currentPrice", is(stockPrice.doubleValue())));

        //update price of the previous stock
        mockMvc.perform(MockMvcRequestBuilders.put("/api/stocks/6")
                .content(toJson(new StockDTO(newPrice)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(stockName)))
                .andExpect(jsonPath("currentPrice", is(newPrice.doubleValue())));

        //Attempt to update a non-existent stock
        mockMvc.perform(MockMvcRequestBuilders.put("/api/stocks/100")
                .content(toJson(new StockDTO(newPrice)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        BigDecimal[] pricesToTest = {
                null, // no content
                BigDecimal.valueOf(-4500.00) //Negative price amount
        };

        for (BigDecimal price : pricesToTest) {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                    .content(toJson(new StockDTO(price)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

    }

    private byte[] toJson(Object object) throws Exception {
        ObjectMapper map = new ObjectMapper();

        return object != null ? map.writeValueAsString(object).getBytes() : null;
    }

}
