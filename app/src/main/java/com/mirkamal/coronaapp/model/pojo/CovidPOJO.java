package com.mirkamal.coronaapp.model.pojo;

import com.squareup.moshi.Json;

import java.util.List;

public class CovidPOJO {

    @Json(name = "Countries")
    private List<CountryPOJO> countries;

    @Json(name = "Global")
    private Global global;

    public CovidPOJO(List<CountryPOJO> countries) {
        this.countries = countries;
    }

    public List<CountryPOJO> getCountries() {
        return countries;
    }

    public Global getGlobal() {
        return global;
    }
}
