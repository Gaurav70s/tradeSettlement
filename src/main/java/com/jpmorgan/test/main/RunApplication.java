package com.jpmorgan.test.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.test.dao.Trade;
import com.jpmorgan.test.service.TradeSettlementService;
import com.jpmorgan.test.service.impl.TradeSettlementServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RunApplication {

    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();
        TradeSettlementService tsService = new TradeSettlementServiceImpl();
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            File file = new File(classLoader.getResource("data/trade.json").getFile());
            List<Trade> tradeList= mapper.readValue(file, new TypeReference<List<Trade>>(){});
        for(Trade trades : tradeList){
            tsService.settlement(trades);
        }
        tsService.settlementReport(tsService.getTradeReport(tradeList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
