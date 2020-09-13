package com.mirkamal.coronaapp.ui.fragments.dialogs.picktimeinterval;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.utils.callbacks.TextChangerCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickTimeIntervalDialogFragment extends DialogFragment {

    @BindView(R.id.number_picker)
    NumberPicker picker;

    SharedPreferences preferences;

    TextChangerCallback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_pick_time_interval, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        assert getArguments() != null;
        callback = PickTimeIntervalDialogFragmentArgs.fromBundle(getArguments()).getHelper().getCallback();

        configureSharedPreferences();
        configureNumberPicker();
    }

    private void configureSharedPreferences() {
        preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
    }

    private void configureNumberPicker() {
        picker.setMaxValue(24);
        picker.setMinValue(1);

        picker.setValue(preferences.getInt("notificationInterval", 12));
    }

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @OnClick(R.id.button_cancel)
    void onCancelClicked() {
        dismiss();
    }

    @OnClick(R.id.button_set)
    void onSetClicked() {
        //TODO save to shared preferences and update the text in settings fragment

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("notificationInterval", picker.getValue());
        editor.apply();

        callback.handleTextChange(picker.getValue());

        dismiss();
    }
}
