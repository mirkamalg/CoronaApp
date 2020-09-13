package com.mirkamal.coronaapp.utils.lib;

import android.os.Parcel;
import android.os.Parcelable;

import com.mirkamal.coronaapp.utils.callbacks.TextChangerCallback;

public class TextChangeCallbackHelper implements Parcelable {

    private TextChangerCallback callback;

    public TextChangeCallbackHelper(TextChangerCallback callback) {
        this.callback = callback;
    }

    public TextChangerCallback getCallback() {
        return callback;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.callback, flags);
    }

    protected TextChangeCallbackHelper(Parcel in) {
        this.callback = in.readParcelable(TextChangerCallback.class.getClassLoader());
    }

    public static final Creator<TextChangeCallbackHelper> CREATOR = new Creator<TextChangeCallbackHelper>() {
        @Override
        public TextChangeCallbackHelper createFromParcel(Parcel source) {
            return new TextChangeCallbackHelper(source);
        }

        @Override
        public TextChangeCallbackHelper[] newArray(int size) {
            return new TextChangeCallbackHelper[size];
        }
    };
}
