package com.jpmorgan.test.main;

import com.jpmorgan.test.dao.Trade;
import com.jpmorgan.test.service.TradeSettlementService;
import com.jpmorgan.test.service.impl.TradeSettlementServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunApplication {

    public static void main(String[] args){
        TradeSettlementService tsService = new TradeSettlementServiceImpl();
        List<Trade> tradeList = new ArrayList<>();
        Trade trade = new Trade();
        trade.setAgreedFx(BigDecimal.valueOf(0.2));
        trade.setInstruction('S');
        trade.setCurrency("NOK");
        trade.setEntity("DNB1");
        trade.setInstructionDate(new Date());
        trade.setSettlementDate(new Date());
        trade.setUnits(100);
        trade.setPricePerUnit(BigDecimal.valueOf(150.5));
        Trade trade1 = new Trade();
        trade1.setAgreedFx(BigDecimal.valueOf(0.3));
        trade1.setInstruction('S');
        trade1.setCurrency("SEK");
        trade1.setEntity("NIC1");
        trade1.setInstructionDate(new Date());
        trade1.setSettlementDate(new Date());
        trade1.setUnits(1000);
        trade1.setPricePerUnit(BigDecimal.valueOf(15.5));
        tradeList.add(trade);
        tradeList.add(trade1);

        for(Trade trades : tradeList){
            tsService.settlement(trades);
        }
        tsService.SettlementReport(tsService.getTradeReport(tradeList));








    }
}
