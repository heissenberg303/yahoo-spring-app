package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter
@With
@AllArgsConstructor
public class StockWrapper {

    final private Stock stock;
    final private LocalDateTime lastAccess;

    public StockWrapper(Stock stock) {
        this.stock = stock;
        this.lastAccess = LocalDateTime.now();
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }
}
