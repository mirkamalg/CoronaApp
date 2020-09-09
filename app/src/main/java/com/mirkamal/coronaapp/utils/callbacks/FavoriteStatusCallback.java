package com.mirkamal.coronaapp.utils.callbacks;

import com.mirkamal.coronaapp.model.entity.Country;

public interface FavoriteStatusCallback {

    void handle(Country country);

}
