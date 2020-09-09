package com.mirkamal.coronaapp.ui.fragments.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.repository.MainRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment {

    @BindView(R.id.recycler_view_favorites)
    RecyclerView recyclerView;

    private MainRepository repository;

    private NavController navController;

    private List<Country> favoriteCountries;

    private FavoriteCountriesListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configureRepository();

        navController = NavHostFragment.findNavController(this);

        ButterKnife.bind(this, view);

        configureRecyclerView();
    }

    private void configureRepository() {
        repository = new MainRepository(getContext());

        favoriteCountries = repository.getFavoriteCountries();

    }

    private void configureRecyclerView() {
        adapter = new FavoriteCountriesListAdapter(new FavoriteCountryDiffCallback(), position -> navController.navigate(FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(favoriteCountries.get(position).getName())));
        adapter.submitList(favoriteCountries);
        recyclerView.setAdapter(adapter);
    }
}
