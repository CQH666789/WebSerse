package com.google.server;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;

public final class NotificationBuilder {
    public static final String NOTIFICATION_CHANNEL_ID = "com.demo.CHANNEL_ID";
    public static final int NOTIFICATION_ID = 9999;

    private final Context mContext;
    private final NotificationManager mNotificationManager;

    public NotificationBuilder(Context context) {
        mContext = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification buildNotification() {
        /*
        if (shouldCreateNowPlayingChannel()) {
            createNowPlayingChannel();
        }
        */
        createNowPlayingChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

        return builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("")
                .setContentTitle("")
                .setOnlyAlertOnce(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean nowPlayingChannelExists() {
        return mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) != null;
    }

    private boolean shouldCreateNowPlayingChannel() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !nowPlayingChannelExists();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNowPlayingChannel() {
        final NotificationChannel channel = new NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "当前播放",
                NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("当前播放的电台");
        mNotificationManager.createNotificationChannel(channel);
    }
}