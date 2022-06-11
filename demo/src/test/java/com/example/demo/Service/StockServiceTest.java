package com.example.demo.Service;

import com.example.demo.Model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    void invoke() throws IOException {
        final StockWrapper stock = stockService.findStock("AAPL");
        System.out.println(stock.getStock());

        final BigDecimal price = stockService.findPrice(stock);
        System.out.println(price);

        final BigDecimal change = stockService.findLastChangePercent(stock);
        System.out.println(change);

        final BigDecimal mean200 = stockService.findChange200MeanPercent(stock);
        System.out.println(mean200);
    }

    @Test
    void multiple() throws IOException, InterruptedException
    {
        final List<StockWrapper> stocks = stockService.findStocks(Arrays.asList("GOOG", "AMZN"));

    }
}