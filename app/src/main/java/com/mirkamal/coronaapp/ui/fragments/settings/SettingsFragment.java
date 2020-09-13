package com.mirkamal.coronaapp.ui.fragments.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.mirkamal.coronaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends Fragment {

    @BindView(R.id.text_view_current_period)
    TextView textViewCurrentPeriod;

    private NavController controller;

    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        configureSharedPreferences();
        configureCurrentNotificationPeriodTextView();

        controller = NavHostFragment.findNavController(this);
    }

    private void configureSharedPreferences() {
        preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @SuppressLint("SetTextI18n")
    private void configureCurrentNotificationPeriodTextView() {
        textViewCurrentPeriod.setText("Currently: " + preferences.getInt("notificationInterval", 12) + " hour(s)");
    }

    @OnClick(R.id.button_pick_time_interval)
    void onPickTimeIntervalButtonClicked() {
        controller.navigate(SettingsFragmentDirections.actionSettingsFragmentToPickTimeIntervalDialogFragment());
    }

    @OnClick(R.id.image_view_arrow)
    void onArrowClicked() {
        controller.popBackStack();
    }
}
