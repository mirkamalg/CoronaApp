package com.mirkamal.coronaapp.utils.lib;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.repository.BackgroundRepository;
import com.mirkamal.coronaapp.repository.MainRepository;

import java.util.Random;

public class NotificationBroadcast extends BroadcastReceiver {

    BackgroundRepository repository;
    MainRepository mainRepository;

    private final String CHANNEL_ID = "CoronaChannelID";
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        repository = new BackgroundRepository(context);
        mainRepository = new MainRepository(context);

        this.context = context;

        createNotificationChannel(context);

        repository.getDataFromNetwork();
        for (Country country:mainRepository.getFavoriteCountries()) {
            sendNotification(country);
        }
    }

    private void sendNotification(Country country) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(country.getName())
                .setContentText("New cases: " + country.getNewCases() + ", New deaths: " + country.getNewDeaths())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(new Random().nextInt(500), builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CoronaApp";
            String description = "Summary";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
