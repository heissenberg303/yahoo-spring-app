package com.example.demo.Service;

import com.example.demo.Model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    void invoke(){
        final StockWrapper stock = stockService.findStock("UU.L");
        System.out.println(stock.getStock());
    }

}