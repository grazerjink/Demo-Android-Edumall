package vn.edu.topica.hocnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTao, btnDong;
    int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoNotification();
            }
        });
        
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dongNotification();
            }
        });
    }

    private void dongNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }

    private void taoNotification() {
        //Buoc 1
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.alarm)
                        .setContentTitle("You have a message")
                        .setContentText("Please check the newest version.");
        //Buoc 2
        Intent resultIntent = new Intent(this,UpdateActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                                                    this,
                                                    0,
                                                    resultIntent,
                                                    PendingIntent.FLAG_UPDATE_CURRENT);
        //Buoc 3
        /*mBuilder.setContentIntent(resultPendingIntent);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);*/
        Uri newSound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.chosua);
        mBuilder.setSound(newSound);
        notificationId = 113;
        //Buoc 4
        NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId,mBuilder.build());


    }

    private void addControls() {
        btnDong = (Button) findViewById(R.id.btnDong);
        btnTao = (Button) findViewById(R.id.btnTao);
    }
}
