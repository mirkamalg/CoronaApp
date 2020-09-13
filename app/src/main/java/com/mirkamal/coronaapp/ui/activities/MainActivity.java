package com.mirkamal.coronaapp.ui.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.utils.lib.NotificationBroadcast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigation();

        configureSharedPreferences();

        configureNotifications();
    }

    private void configureSharedPreferences() {
        preferences = this.getPreferences(Context.MODE_PRIVATE);
    }

    private void configureNotifications() {
        Intent intent = new Intent(MainActivity.this, NotificationBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long userPeriod = preferences.getInt("notificationInterval", 12) * 360000;

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + userPeriod,
                userPeriod, pendingIntent);
    }

    private void configureNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_bar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}