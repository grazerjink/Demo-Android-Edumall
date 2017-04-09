package vn.edu.topica.fcmclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Du phong khi khach hang install app bi loi, ma onToken thi
        //chi run khi install duy nhat 1 lan
        //nen goi ham luu token o day de them 1 lan co the luu vao.
        FirebaseMessaging.getInstance().subscribeToTopic("testfcm");
        String token = FirebaseInstanceId.getInstance().getToken();
        new FirebaseIDTask().execute(token);
    }
}
