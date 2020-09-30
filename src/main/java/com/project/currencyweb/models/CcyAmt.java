package com.project.currencyweb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CcyAmt  {

    @Id
    private String Ccy;
    private double Amt;

    public CcyAmt() {
    }

    public CcyAmt(String ccy, double amt) {
        Ccy = ccy;
        Amt = amt;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public double getAmt() {
        return Amt;
    }

    public void setAmt(double amt) {
        Amt = amt;
    }

    @Override
    public String toString() {
        return "CcyAmt{" +
                "Ccy='" + Ccy + '\'' +
                ", Amt=" + Amt +
                '}';
    }
}
