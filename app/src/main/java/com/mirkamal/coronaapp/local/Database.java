package com.mirkamal.coronaapp.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mirkamal.coronaapp.local.dao.CountryDao;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.utils.Constants;

@androidx.room.Database(entities = Country.class, exportSchema = false, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null) instance = Room.databaseBuilder(context, Database.class, Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        return instance;
    }

    public abstract CountryDao getCountriesDao();

}
