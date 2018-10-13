package com.jpmorgan.test.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Trade {

    private String entity ;
    private char instruction ;
    private BigDecimal agreedFx ;
    private String currency ;
    private Date instructionDate ;
    private Date settlementDate ;
    private Date actualSettlementDate;
    private int units ;
    private BigDecimal pricePerUnit ;
    private BigDecimal settlementAmount;

    public Date getActualSettlementDate() {
        return actualSettlementDate;
    }

    public void setActualSettlementDate(Date actualSettlementDate) {
        this.actualSettlementDate = actualSettlementDate;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public char getInstruction() {
        return instruction;
    }

    public void setInstruction(char instruction) {
        this.instruction = instruction;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
