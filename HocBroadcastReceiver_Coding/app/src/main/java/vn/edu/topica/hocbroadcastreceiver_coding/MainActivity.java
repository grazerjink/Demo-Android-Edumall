package vn.edu.topica.hocbroadcastreceiver_coding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnOK;

    BroadcastReceiver receiverWifi = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo() != null) {
                //Co internet
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                btnOK.setEnabled(true);
            } else {
                btnOK.setEnabled(false);
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiverWifi,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiverWifi != null)
            unregisterReceiver(receiverWifi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK = (Button) findViewById(R.id.btnOK);
    }
}
