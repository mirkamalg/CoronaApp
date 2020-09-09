package com.mirkamal.coronaapp.ui.fragments.favorites;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.utils.callbacks.OnCountryClickedListener;

public class FavoriteCountryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewCountryName;

    private TextView textViewTotalCases;

    private TextView textViewNewCases;

    private TextView textViewNewDeaths;

    private OnCountryClickedListener onCountryClickedListener;

    public FavoriteCountryItemViewHolder(@NonNull View itemView, OnCountryClickedListener onCountryClickedListener) {
        super(itemView);

        this.onCountryClickedListener = onCountryClickedListener;

        itemView.setOnClickListener(this);

        textViewCountryName = itemView.findViewById(R.id.text_view_country_name);
        textViewTotalCases = itemView.findViewById(R.id.text_view_active_cases);
        textViewNewCases = itemView.findViewById(R.id.text_view_new_cases);
        textViewNewDeaths = itemView.findViewById(R.id.text_view_new_deaths);
    }

    @SuppressLint("SetTextI18n")
    public void bind(Country country) {
        textViewCountryName.setText(country.getName());
        textViewTotalCases.setText(country.getTotalCases());
        textViewNewCases.setText("+" + country.getNewCases());
        textViewNewDeaths.setText("+" + country.getNewDeaths());
    }

    @Override
    public void onClick(View view) {
        onCountryClickedListener.onCountryClicked(getAdapterPosition());
    }
}
