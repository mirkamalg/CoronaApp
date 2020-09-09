package com.mirkamal.coronaapp.network.service;

import com.mirkamal.coronaapp.utils.Constants;
import com.squareup.moshi.Moshi;

import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiInitHelperForBackground {

    private static ApiInitHelperForBackground instance;

    private Retrofit retrofit;

    private CountriesService service;

    public static ApiInitHelperForBackground getInstance() {
        if (instance == null) {
            instance = new ApiInitHelperForBackground();
        }
        return instance;
    }

    private ApiInitHelperForBackground() {
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
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }

    public CountriesService getService() {
        return service;
    }
}

