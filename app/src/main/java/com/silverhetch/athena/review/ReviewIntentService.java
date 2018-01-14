package com.silverhetch.athena.review;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.silverhetch.athena.R;
import com.silverhetch.athena.vocabulary.VocabulariesFactory;
import com.silverhetch.athena.vocabulary.Vocabulary;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

/**
 * Created by mikes on 1/14/2018.
 */
public class ReviewIntentService extends IntentService {
    public ReviewIntentService() {
        super("ReviewIntent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Vocabulary[] vocabularies = new VocabulariesFactory(this).vocabularies().all();
            if (vocabularies.length ==0) {
                return;
            }
            final int randomIndex = ((int) (Math.random() * (vocabularies.length - 1)));
            final String contentText = getString(R.string.review_vocabulary, vocabularies[randomIndex].value(), vocabularies[randomIndex].translation());
            initChannel(getSystemService(NotificationManager.class));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Review");
            builder.setSmallIcon(R.mipmap.ic_launcher_foreground);
            builder.setContentTitle(getString(R.string.review_notificationTitle));
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(contentText));
            builder.setContentText(contentText);
            Notification notification = builder.build();
            NotificationManagerCompat.from(this).notify("Review", 1023, notification);
        } catch (Exception ignore) {
            // nothing we can do here, just don`t crash.
        }
    }

    private void initChannel(NotificationManager notificationManager) {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationChannel channel = new NotificationChannel("Review", "Review", IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
