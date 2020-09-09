package com.mirkamal.coronaapp.utils.lib;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mirkamal.coronaapp.R;
import com.mirkamal.coronaapp.model.entity.Country;
import com.mirkamal.coronaapp.repository.BackgroundRepository;
import com.mirkamal.coronaapp.repository.MainRepository;

import java.util.List;
import java.util.Random;

public class BackgroundWorker extends Worker {

    BackgroundRepository repository;
    MainRepository mainRepository;

    private final String CHANNEL_ID = "CoronaChannelID";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        createNotificationChannel(context);

        repository = new BackgroundRepository(getApplicationContext());
        mainRepository = new MainRepository(getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {


        try {
            repository.getDataFromNetwork();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.retry();
        }

        List<Country> countries = mainRepository.getFavoriteCountries();

        for (Country country:countries) {
            sendNotification(country);
            Log.d("BACKGROUND WORKER", country.getName().toUpperCase());
        }

        return Result.success();
    }

    private void sendNotification(Country country) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(country.getName())
                .setContentText("New cases: " + country.getNewCases() + ", New deaths: " + country.getNewDeaths())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

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
