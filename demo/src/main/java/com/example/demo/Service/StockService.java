package com.example.demo.Service;

import com.example.demo.Model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StockService
{

    private final RefreshService refreshService = new RefreshService();

    public StockWrapper findStock(String ticker)
    {
        try
        {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Error in findStock method");
        }
        return null;
    }

    public List<StockWrapper> findStocks(final List<String> tickers)
    {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());

    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException
    {
//        return stock.getStock().getQuote(true).getPrice();
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice();
    }

    public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException
    {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
    }

    public BigDecimal findChange200MeanPercent(final StockWrapper stock) throws IOException
    {
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
    }
}


