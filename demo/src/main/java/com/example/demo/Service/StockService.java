package com.example.demo.Service;

import com.example.demo.Model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService {

    public StockWrapper findStock(String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Error in findStock method");
        }
        return null;
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException{
        return stock.getStock().getQuote(true).getPrice();
    }
}


