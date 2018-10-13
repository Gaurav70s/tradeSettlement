package com.jpmorgan.test.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TradeReport {

    Date settlementDate;
    BigDecimal IncomingFund;
    BigDecimal OutgoingFund;
    Map<String, BigDecimal> incomingEntity;
    Map<String, BigDecimal> outgoingEntity;

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getIncomingFund() {
        return IncomingFund;
    }

    public void setIncomingFund(BigDecimal incomingFund) {
        IncomingFund = incomingFund;
    }

    public BigDecimal getOutgoingFund() {
        return OutgoingFund;
    }

    public void setOutgoingFund(BigDecimal outgoingFund) {
        OutgoingFund = outgoingFund;
    }

    public Map<String, BigDecimal> getIncomingEntity() {
        return incomingEntity;
    }

    public void setIncomingEntity(Map<String, BigDecimal> incomingEntity) {
        this.incomingEntity = incomingEntity;
    }

    public Map<String, BigDecimal> getOutgoingEntity() {
        return outgoingEntity;
    }

    public void setOutgoingEntity(Map<String, BigDecimal> outgoingEntity) {
        this.outgoingEntity = outgoingEntity;
    }




}
