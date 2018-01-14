package com.silverhetch.athena.review;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.app.AlarmManager.RTC;

/**
 * Created by mikes on 1/14/2018.
 */

public class NotificationReviewImpl implements NotificationReview {
    private final static long INTERVAL = 1000 * 60 * 60 * 6;
    private final Context context;

    public NotificationReviewImpl(Context context) {
        this.context = context;
    }

    @Override
    public void schedule() {
        AlarmManager alarmManager = context.getSystemService(AlarmManager.class);
        alarmManager.setRepeating(RTC, INTERVAL, INTERVAL, pendingIntent());
    }

    @Override
    public void cancelAll() {
        AlarmManager alarmManager = context.getSystemService(AlarmManager.class);
        alarmManager.cancel(pendingIntent());
    }

    private PendingIntent pendingIntent() {
        Intent intent = new Intent(context, ReviewIntentService.class);
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
