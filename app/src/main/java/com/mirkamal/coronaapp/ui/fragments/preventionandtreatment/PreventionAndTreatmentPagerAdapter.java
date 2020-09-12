package com.mirkamal.coronaapp.ui.fragments.preventionandtreatment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mirkamal.coronaapp.ui.fragments.prevention.PreventionFragment;
import com.mirkamal.coronaapp.ui.fragments.treatment.TreatmentFragment;

import java.util.ArrayList;
import java.util.List;

public class PreventionAndTreatmentPagerAdapter extends FragmentStateAdapter {

    List<Fragment> fragments;

    public PreventionAndTreatmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments = new ArrayList<>();
        fragments.add(new PreventionFragment());
        fragments.add(new TreatmentFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
