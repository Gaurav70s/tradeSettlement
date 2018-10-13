package com.jpmorgan.test.service;

import com.jpmorgan.test.dao.Trade;
import com.jpmorgan.test.dao.TradeReport;

import java.util.List;

public interface TradeSettlementService {

    void settlement(Trade trade);

    TradeReport getTradeReport(List<Trade> tradeList);

    void settlementReport(TradeReport report);
}
