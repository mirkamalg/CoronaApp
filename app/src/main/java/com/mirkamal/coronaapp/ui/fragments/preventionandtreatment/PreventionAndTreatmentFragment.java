package com.mirkamal.coronaapp.ui.fragments.preventionandtreatment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mirkamal.coronaapp.R;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreventionAndTreatmentFragment extends Fragment {

    @BindView(R.id.view_pager_prevention_and_treatment)
    ViewPager2 viewPager;

    @BindView(R.id.page_indicator_view)
    PageIndicatorView pageIndicatorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prevention_and_treatment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        viewPager.setAdapter(new PreventionAndTreatmentPagerAdapter(requireActivity()));

        configurePageIndicatorView();
    }

    private void configurePageIndicatorView() {
        pageIndicatorView.setCount(2);
        pageIndicatorView.setSelection(1);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                pageIndicatorView.setSelection(position);
            }
        });
    }

    @OnClick(R.id.button_visit_who_website)
    void onButtonClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/health-topics/coronavirus#tab=tab_2"));
        startActivity(browserIntent);
    }
}
