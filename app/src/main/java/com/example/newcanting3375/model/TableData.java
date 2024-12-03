package com.example.newcanting3375.model;

import java.util.List;

public class TableData {
    private String city;
    private List<Double> monthlyValues;

    public TableData(String city, List<Double> monthlyValues) {
        this.city = city;
        this.monthlyValues = monthlyValues;
    }

    public String getCity() {
        return city;
    }

    public List<Double> getMonthlyValues() {
        return monthlyValues;
    }
}

