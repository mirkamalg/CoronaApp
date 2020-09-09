package com.mirkamal.coronaapp.ui.fragments.home;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.utils.callbacks.OnCountryClickedListener;

public class CountryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewName;

    private TextView textViewTotalCases;

    private TextView textViewNewCases;

    private OnCountryClickedListener onCountryClickedListener;

    public CountryItemViewHolder(@NonNull View itemView, OnCountryClickedListener onCountryClickedListener) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.text_view_country_name);
        textViewTotalCases = itemView.findViewById(R.id.text_view_total_cases);
        textViewNewCases = itemView.findViewById(R.id.text_view_new_cases);

        this.onCountryClickedListener = onCountryClickedListener;

        itemView.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void bind(Country country) {
        textViewName.setText(country.getName());
        textViewTotalCases.setText(country.getTotalCases());
        textViewNewCases.setText("+" + country.getNewCases());
    }

    @Override
    public void onClick(View view) {
        onCountryClickedListener.onCountryClicked(getAdapterPosition());
    }
}
