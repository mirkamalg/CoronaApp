package com.mirkamal.coronaapp.utils.lib;

public class Observable<K> extends java.util.Observable {

    private K value;

    public void setValue(K value) {
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public K getValue() {
        return value;
    }

}
