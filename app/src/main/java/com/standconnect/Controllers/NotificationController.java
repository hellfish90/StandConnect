package com.standconnect.Controllers;

import com.standconnect.DetailActivityContainer;
import com.standconnect.EventContainer;
import com.standconnect.Models.Stand;
import com.standconnect.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;

/**
 * Created by Marc on 25/11/15.
 */
public class NotificationController {

    public static final String ARG_STAND_NOTIFICATION = "STAND_NOTIFICATION";

    public void showNotification(Stand stand, Activity act){

        int notificationId = 001;
// Build intent for notification content
        Intent viewIntent = new Intent(act, DetailActivityContainer.class);
        Bundle extras = new Bundle();

        extras.putSerializable(DetailActivityContainer.ARG_DETAIL_CONTENT_ENTITY, stand);

        Log.d("Notification", stand.toString());

        viewIntent.putExtras(extras);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(act, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(act)
                        .setSmallIcon(R.mipmap.ic_business_white_48dp)
                        .setContentTitle("StandConnect")
                        .setContentText(stand.getName())
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.mipmap.ic_business_white_48dp,
                                "Open app", viewPendingIntent);

// Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(act);

// Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
}
