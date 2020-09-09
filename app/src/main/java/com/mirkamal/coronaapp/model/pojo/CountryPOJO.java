package com.mirkamal.coronaapp.model.pojo;

import com.squareup.moshi.Json;

public class CountryPOJO {

    @Json(name = "Country")
    private String name;

    @Json(name = "TotalConfirmed")
    private String totalCases;

    @Json(name = "NewConfirmed")
    private String newCases;

    @Json(name = "TotalDeaths")
    private String totalDeaths;

    @Json(name = "NewDeaths")
    private String newDeaths;

    @Json(name = "TotalRecovered")
    private String totalRecovered;

    @Json(name = "NewRecovered")
    private String newRecovered;

    public CountryPOJO(String name, String totalCases, String newCases, String totalDeaths, String newDeaths, String totalRecovered, String newRecovered) {
        this.name = name;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        this.newRecovered = newRecovered;
    }

    public String getName() {
        return name;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public String getNewCases() {
        return newCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public String getNewRecovered() {
        return newRecovered;
    }
}
