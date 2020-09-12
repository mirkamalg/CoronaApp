package com.mirkamal.coronaapp.ui.fragments.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.mirkamal.coronaapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoFragment extends Fragment {

    NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        controller = NavHostFragment.findNavController(this);
    }

    @OnClick(R.id.view_about_virus)
    void onAboutVirusClicked() {
        controller.navigate(InfoFragmentDirections.actionInfoFragmentToAboutCoronavirusFragment());
    }

    @OnClick(R.id.view_symptoms)
    void onSymptomsClicked() {
        controller.navigate(InfoFragmentDirections.actionInfoFragmentToSymptomsFragment());
    }

    @OnClick(R.id.view_prevention_and_treatment)
    void onPreventionAndTreatmentClicked() {
        controller.navigate(InfoFragmentDirections.actionInfoFragmentToPreventionAndTreatmentFragment());
    }

}
