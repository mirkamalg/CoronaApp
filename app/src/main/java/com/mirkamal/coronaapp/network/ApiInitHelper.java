package com.mirkamal.coronaapp.network;

import com.mirkamal.coronaapp.network.service.CountriesService;
import com.mirkamal.coronaapp.utils.Constants;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiInitHelper {

    private static ApiInitHelper instance;

    private Retrofit retrofit;

    private CountriesService service;

    public static ApiInitHelper getInstance() {
        if (instance == null) {
            instance = new ApiInitHelper();
        }
        return instance;
    }

    private ApiInitHelper() {
        service = getClient().create(CountriesService.class);
    }

    private Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(
                            new Moshi.Builder().build()
                            )
                    )
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public CountriesService getService() {
        return service;
    }
}
