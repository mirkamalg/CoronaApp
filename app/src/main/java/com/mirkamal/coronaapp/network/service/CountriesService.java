package com.mirkamal.coronaapp.network.service;

import com.mirkamal.coronaapp.model.pojo.CovidPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesService {

    @GET("/summary")
    Call<CovidPOJO> getSummaryData();

}
