package com.mirkamal.coronaapp.repository;

import android.content.Context;

import com.mirkamal.coronaapp.local.Database;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.model.pojo.CountryPOJO;
import com.mirkamal.coronaapp.model.pojo.CovidPOJO;
import com.mirkamal.coronaapp.network.service.CountriesService;
import com.mirkamal.coronaapp.utils.callbacks.DataRequestCallback;
import com.mirkamal.coronaapp.utils.callbacks.FavoriteStatusCallback;
import com.mirkamal.coronaapp.utils.lib.Converter;
import com.mirkamal.coronaapp.utils.lib.Observable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private Observable<List<CountryPOJO>> observable = new Observable<>();

    private CountriesService service;

    private DataRequestCallback callback;

    private FavoriteStatusCallback favoriteStatusCallback;

    private Database database;

    public MainRepository(CountriesService service, Context context, DataRequestCallback callback) {
        this.service = service;
        this.callback = callback;
        database = Database.getInstance(context);
    }

    public MainRepository(Context context, FavoriteStatusCallback favoriteStatusCallback) {
        database = Database.getInstance(context);
        this.favoriteStatusCallback = favoriteStatusCallback;
    }

    public MainRepository(Context context) {
        this.database = Database.getInstance(context);
    }

    public void getDataFromNetwork() {
        service.getSummaryData().enqueue(new Callback<CovidPOJO>() {
            @Override
            public void onResponse(Call<CovidPOJO> call, Response<CovidPOJO> response) {

                List<CountryPOJO> countryPOJOS = new ArrayList<>();

                if (response.code() == 200 && response.body() != null) {
                    countryPOJOS = response.body().getCountries();

                    observable.setValue(countryPOJOS);
                }

                callback.onSuccessfulRequest();

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
                callback.onFailedRequest();
            }
        });
    }

    public void handleFavoriteCountryAction(Country country) {

        favoriteStatusCallback.handle(country);

        if (country.getFavorite().equals("false")) {
            database.getCountriesDao().changeToFavorite(country.getName());
        } else {
            database.getCountriesDao().changeToNotFavorite(country.getName());
        }
    }

    public List<Country> getFavoriteCountries() {
        return database.getCountriesDao().getFavoriteCountries();
    }

    public Observable<List<CountryPOJO>> getObservable() {
        return observable;
    }


}
