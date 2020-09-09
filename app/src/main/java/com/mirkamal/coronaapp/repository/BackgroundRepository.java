package com.mirkamal.coronaapp.repository;

import android.content.Context;

import com.mirkamal.coronaapp.local.Database;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.model.pojo.CountryPOJO;
import com.mirkamal.coronaapp.model.pojo.CovidPOJO;
import com.mirkamal.coronaapp.network.service.ApiInitHelperForBackground;
import com.mirkamal.coronaapp.network.service.CountriesService;
import com.mirkamal.coronaapp.utils.lib.Converter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundRepository {

    private CountriesService service;

    private Database database;

    public BackgroundRepository(Context context) {
        database = Database.getInstance(context);
        service = ApiInitHelperForBackground.getInstance().getService();
    }

    public void getDataFromNetwork() {
        service.getSummaryData().enqueue(new Callback<CovidPOJO>() {
            @Override
            public void onResponse(Call<CovidPOJO> call, Response<CovidPOJO> response) {

                List<CountryPOJO> countryPOJOS = new ArrayList<>();

                if (response.code() == 200 && response.body() != null) {
                    countryPOJOS = response.body().getCountries();
                }

                List<Country> countries = new ArrayList<>();
                for (CountryPOJO countryPOJO : countryPOJOS) {
                    Country country = Converter.convertCountryPOJOToCountry(countryPOJO);

                    try {
                        if (database.getCountriesDao().getCountryByName(country.getName()).getFavorite().equals("true")) {
                            country.setFavorite("true");
                        }
                    } catch (Exception ignored) {}

                    countries.add(country);
                }

                database.getCountriesDao().addToDatabase(countries);
            }

            @Override
            public void onFailure(Call<CovidPOJO> call, Throwable t) {
            }
        });
    }

}
