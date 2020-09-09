package com.mirkamal.coronaapp.ui.fragments.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.local.Database;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.repository.MainRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsFragment extends Fragment {

    @BindView(R.id.text_view_country_name)
    TextView textViewCountryName;

    @BindView(R.id.text_view_total_cases_count)
    TextView textViewTotalCases;

    @BindView(R.id.text_view_new_cases_count)
    TextView textViewNewCases;

    @BindView(R.id.text_view_total_recovered_count)
    TextView textViewTotalRecovered;

    @BindView(R.id.text_view_new_recovered_count)
    TextView textViewNewRecovered;

    @BindView(R.id.text_view_total_death_count)
    TextView textViewTotalDeaths;

    @BindView(R.id.text_view_new_death_count)
    TextView textViewNewDeaths;

    @BindView(R.id.fab_favorite)
    FloatingActionButton fab;

    private MainRepository repository;

    private Country country;

    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        navController = NavHostFragment.findNavController(this);

        repository = new MainRepository(getContext(), country -> {
            if (country.getFavorite().equals("true"))
                Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
            else Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
        });

        fetchData();
        configureFab();
    }

    private void configureFab() {
        fab.setOnClickListener(view -> repository.handleFavoriteCountryAction(country));
    }

    private void fetchData() {
        assert getArguments() != null;
        String name = DetailsFragmentArgs.fromBundle(getArguments()).getCountryName();

        country = Database.getInstance(getContext()).getCountriesDao().getCountryByName(name);

        setTextViews(country);
    }

    @SuppressLint("SetTextI18n")
    private void setTextViews(Country country) {
        textViewCountryName.setText(country.getName());
        textViewTotalCases.setText(country.getTotalCases());
        textViewNewCases.setText("+" + country.getNewCases());
        textViewTotalRecovered.setText(country.getTotalRecovered());
        textViewNewRecovered.setText("+" + country.getNewRecovered());
        textViewTotalDeaths.setText(country.getTotalDeaths());
        textViewNewDeaths.setText("+" + country.getNewDeaths());
    }

    @OnClick(R.id.image_view_arrow)
    void onArrowClicked() {
        navController.popBackStack();
    }
}
