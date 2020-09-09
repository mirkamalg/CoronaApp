package com.mirkamal.coronaapp.ui.fragments.favorites;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.mirkamal.coronaapp.model.entity.Country;

public class FavoriteCountryDiffCallback extends DiffUtil.ItemCallback<Country> {
    @Override
    public boolean areItemsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
        return oldItem == newItem;
    }
}
