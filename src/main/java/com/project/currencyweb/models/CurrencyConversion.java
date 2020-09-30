package com.project.currencyweb.models;

public class CurrencyConversion {

            private String to;
            private String from;
            private double value;

    public CurrencyConversion() {
    }

    public CurrencyConversion(String to, String from, double value) {
        this.to = to;
        this.from = from;
        this.value = value;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
