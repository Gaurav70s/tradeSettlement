package com.jpmorgan.test.service.impl;

import com.jpmorgan.test.dao.Trade;
import com.jpmorgan.test.dao.TradeReport;
import com.jpmorgan.test.service.TradeSettlementService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class TradeSettlementServiceImpl implements TradeSettlementService {

    public void settlement(Trade trade) {
        Calendar date = Calendar.getInstance();
        date.setTime(trade.getSettlementDate());
        if(trade.getCurrency().equalsIgnoreCase("SAR") || trade.getCurrency().equalsIgnoreCase("AED")){
            if(date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                while (date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    date.add(Calendar.DATE, 1);
                }
                trade.setActualSettlementDate(date.getTime());
            }else{
                trade.setActualSettlementDate(trade.getSettlementDate());
            }
        }else{
            if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                while (date.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                    date.add(Calendar.DATE, 1);
                }
                trade.setActualSettlementDate(date.getTime());
            }else{
                trade.setActualSettlementDate(trade.getSettlementDate());
            }
        }
        trade.setSettlementAmount(trade.getAgreedFx().multiply(new BigDecimal(trade.getUnits())).multiply(trade.getPricePerUnit()));
    }

    public TradeReport getTradeReport(List<Trade> tradeList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        TradeReport report = new TradeReport();
        report.setSettlementDate(new Date());
        BigDecimal incomingAmount=BigDecimal.ZERO;
        BigDecimal outgoingAmount=BigDecimal.ZERO;
        Map<String,BigDecimal> incomingEntity = new HashMap<>();
        Map<String,BigDecimal> outgoingEntity = new HashMap<>();
        for(Trade trade : tradeList) {
            if(dateFormat.format(new Date()).equalsIgnoreCase(dateFormat.format(trade.getActualSettlementDate()))){
                if(trade.getInstruction() == 'S') {
                    incomingAmount = incomingAmount.add(trade.getSettlementAmount());
                    incomingEntity.put(trade.getEntity(),trade.getSettlementAmount());
                }else if(trade.getInstruction() == 'B'){
                    outgoingAmount = outgoingAmount.add(trade.getSettlementAmount());
                    outgoingEntity.put(trade.getEntity(),trade.getSettlementAmount());
                }
            }
        }
        report.setIncomingFund(incomingAmount);
        report.setOutgoingFund(outgoingAmount);
        report.setIncomingEntity(incomingEntity);
        report.setOutgoingEntity(outgoingEntity);
        return report;
    }

    public void settlementReport(TradeReport report) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Stream<Map.Entry<String,BigDecimal>> sortedIncomingEntity =
                report.getIncomingEntity().entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        Stream<Map.Entry<String,BigDecimal>> sortedOutgoingEntity =
                report.getOutgoingEntity().entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println("********************************************************************");
        System.out.println("                      End of the Day Report                         ");
        System.out.println("********************************************************************");
        System.out.println("Date of Report      : "+dateFormat.format(report.getSettlementDate()));
        System.out.println("Total Incoming Fund : "+report.getIncomingFund() + " USD");
        System.out.println("Total Outgoing Fund : "+report.getOutgoingFund() + " USD");
        System.out.println("*********************************************************************");
        if(!report.getIncomingEntity().isEmpty()) {
            System.out.println("Ranking of Incoming Fund");
            System.out.println("Rank    Entity  Amount");
            sortedIncomingEntity.forEach(e -> System.out.println(counter.addAndGet(1) + "      " + e.getKey() + "        " + e.getValue()+ " USD"));
        }
        if(!report.getOutgoingEntity().isEmpty()) {
            System.out.println("*********************************************************************");
            System.out.println("Ranking of Outgoing Fund");
            System.out.println("Rank    Entity  Amount");
            counter.getAndSet(0);
            sortedOutgoingEntity.forEach(e -> System.out.println(counter.addAndGet(1) + "      " + e.getKey() + "        " + e.getValue()+ " USD"));
        }
        System.out.println("********************************************************************");
        System.out.println("                         End of Report                         ");
        System.out.println("********************************************************************");




    }
}
