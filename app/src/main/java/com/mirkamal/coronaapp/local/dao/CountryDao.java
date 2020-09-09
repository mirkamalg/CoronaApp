package com.mirkamal.coronaapp.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mirkamal.coronaapp.model.entity.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToDatabase(List<Country> countries);

    @Query("update countries set is_favorite='true' where name= :name and is_favorite='false'")
    void changeToFavorite(String name);

    @Query("update countries set is_favorite='false' where name= :name and is_favorite='true'")
    void changeToNotFavorite(String name);

    @Query("select * from countries")
    List<Country> getAllCountriesFromDatabase();

    @Query("select * from countries where name = :name")
    Country getCountryByName(String name);

    @Query("select * from countries where is_favorite = 'true'")
    List<Country> getFavoriteCountries();

}
