package com.mirkamal.coronaapp.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "countries")
public class Country {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "total_cases")
    private String totalCases;

    @ColumnInfo(name = "new_cases")
    private String newCases;

    @ColumnInfo(name = "total_deaths")
    private String totalDeaths;

    @ColumnInfo(name = "new_deaths")
    private String newDeaths;

    @ColumnInfo(name = "total_recovered")
    private String totalRecovered;

    @ColumnInfo(name = "new_recovered")
    private String newRecovered;

    @ColumnInfo(name = "is_favorite")
    private String favorite = "false";

    public Country(@NotNull String name, String totalCases, String newCases, String totalDeaths
            , String newDeaths, String totalRecovered, String newRecovered) {
        this.name = name;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        this.newRecovered = newRecovered;
    }

    @NotNull
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

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
