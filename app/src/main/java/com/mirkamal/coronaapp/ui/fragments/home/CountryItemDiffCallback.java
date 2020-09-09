package com.mirkamal.coronaapp.ui.fragments.home;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.mirkamal.coronaapp.model.pojo.CountryPOJO;

public class CountryItemDiffCallback extends DiffUtil.ItemCallback<CountryPOJO> {

    @Override
    public boolean areItemsTheSame(@NonNull CountryPOJO oldItem, @NonNull CountryPOJO newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull CountryPOJO oldItem, @NonNull CountryPOJO newItem) {
        return oldItem == newItem;
    }
}
