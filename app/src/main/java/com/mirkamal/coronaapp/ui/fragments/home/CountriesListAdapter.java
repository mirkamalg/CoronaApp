package com.mirkamal.coronaapp.ui.fragments.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.model.pojo.CountryPOJO;
import com.mirkamal.coronaapp.utils.callbacks.OnCountryClickedListener;

public class CountriesListAdapter extends ListAdapter<CountryPOJO, CountryItemViewHolder> {

    private OnCountryClickedListener onCountryClickedListener;

    protected CountriesListAdapter(@NonNull DiffUtil.ItemCallback<CountryPOJO> diffCallback, OnCountryClickedListener onCountryClickedListener) {
        super(diffCallback);

        this.onCountryClickedListener = onCountryClickedListener;
    }

    @NonNull
    @Override
    public CountryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CountryItemViewHolder(inflater.inflate(R.layout.item_countries_list, parent, false), onCountryClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryItemViewHolder holder, int position) {
        CountryPOJO countryPOJO = getItem(position);
        Country country = new Country(countryPOJO.getName(), countryPOJO.getTotalCases(), countryPOJO.getNewCases()
                , countryPOJO.getTotalDeaths(), countryPOJO.getNewDeaths(), countryPOJO.getTotalRecovered()
                , countryPOJO.getNewRecovered());
        holder.bind(country);
    }
}
