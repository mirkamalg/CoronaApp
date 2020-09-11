package com.mirkamal.coronaapp.ui.fragments.aboutcoronavirus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirkamal.coronaapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutCoronavirusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_coronavirus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_visit_who_website)
    void onButtonClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/health-topics/coronavirus#tab=tab_1"));
        startActivity(browserIntent);
    }
}
