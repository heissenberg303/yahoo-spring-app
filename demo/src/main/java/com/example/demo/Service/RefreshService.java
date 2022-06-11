package com.example.demo.Service;

import com.example.demo.Model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class RefreshService
{
    private final Map<StockWrapper, Boolean> stocksToRefresh;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final Duration refreshPeriod = Duration.ofSeconds(15);

    // Constructor call default setRefreshEvery15Minutes() method
    public RefreshService()
    {
        stocksToRefresh = new HashMap<>();
        setRefreshEvery15Minutes();
    }

    public boolean shouldRefresh(final StockWrapper stock)
    {
        if (!stocksToRefresh.containsKey(stock))
        {
            stocksToRefresh.put(stock, false);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    private void setRefreshEvery15Minutes()
    {
        scheduler.scheduleAtFixedRate(() ->
            stocksToRefresh.forEach((stock, value) -> {
                // if not access for 15 secs
                if (stock.getLastAccess().isBefore(LocalDateTime.now().minus(refreshPeriod)))
                {
                    System.out.println("Setting should refresh " + stock.getStock().getSymbol());
                    stocksToRefresh.remove(stock);
                    stocksToRefresh.put(stock.withLastAccess(LocalDateTime.now()), true);
                }
            }), 0, 15, SECONDS);
    }
}
