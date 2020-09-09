package com.mirkamal.coronaapp.model.pojo;

public class Global {

    private String newCases;

    private String totalCases;

    private String newDeaths;

    private String totalDeaths;

    private String newRecovered;

    private String totalRecovered;

    public Global(String newCases, String totalCases, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered) {
        this.newCases = newCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
    }

    public String getNewCases() {
        return newCases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public String getNewRecovered() {
        return newRecovered;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }
}
