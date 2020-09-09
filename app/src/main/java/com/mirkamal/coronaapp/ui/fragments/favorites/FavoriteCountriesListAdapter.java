package com.mirkamal.coronaapp.ui.fragments.favorites;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.utils.callbacks.OnCountryClickedListener;

public class FavoriteCountriesListAdapter extends ListAdapter<Country, FavoriteCountryItemViewHolder> {

    private OnCountryClickedListener onCountryClickedListener;

    protected FavoriteCountriesListAdapter(@NonNull DiffUtil.ItemCallback<Country> diffCallback, OnCountryClickedListener onCountryClickedListener) {
        super(diffCallback);

        this.onCountryClickedListener = onCountryClickedListener;
    }

    @NonNull
    @Override
    public FavoriteCountryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FavoriteCountryItemViewHolder(inflater.inflate(R.layout.item_favorites_list, parent, false), onCountryClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteCountryItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
