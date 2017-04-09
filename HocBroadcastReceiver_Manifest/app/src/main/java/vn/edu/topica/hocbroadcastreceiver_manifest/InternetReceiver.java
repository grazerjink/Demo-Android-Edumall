package vn.edu.topica.hocbroadcastreceiver_manifest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null) {
            xuLyMoThongBao(context);
        }
        
    }

    private void xuLyMoThongBao(Context context) {
        //Buoc 1
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.alarm)
                        .setContentTitle("You have a message")
                        .setContentText("Please check the newest version.");
        //Buoc 2
        Intent resultIntent = new Intent(context,MainActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //Buoc 3
        mBuilder.setContentIntent(resultPendingIntent);
        /*Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);*/
        Uri newSound = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.chosua);
        mBuilder.setSound(newSound);
        //Buoc 4
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(114,mBuilder.build());
    }
}
