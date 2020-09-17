package com.mirkamal.coronaapp.utils.callbacks;

public interface DataRequestCallback {

    void onSuccessfulRequest();

    void onFailedRequest();

    default void onApiCaching() {};

}
