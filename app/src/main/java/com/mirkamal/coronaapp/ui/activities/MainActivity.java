package com.mirkamal.coronaapp.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.utils.lib.BackgroundWorker;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        configureNavigation();

        configureWorker();
    }

    private void configureWorker() {

        // If app is opened for the first time
        if (isBackgroundWorkNeededToBeInitialized()) {
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build();

            String TAG = UUID.randomUUID().toString();
            PeriodicWorkRequest periodicNotificationSender =
                    new PeriodicWorkRequest.Builder(BackgroundWorker.class, 15, TimeUnit.MINUTES)
                            .addTag(TAG)
                            .setConstraints(constraints)
                            .build();

            WorkManager workManager = WorkManager.getInstance();
            workManager.enqueue(periodicNotificationSender);
        }
        setSharedPreferenceData();
    }

    private void configureNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_bar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    private boolean isBackgroundWorkNeededToBeInitialized() {
        return sharedPreferences.getBoolean(getString(R.string.preferenceCode), false);
    }

    private void setSharedPreferenceData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.preferenceCode), true);
        editor.apply();
    }
}