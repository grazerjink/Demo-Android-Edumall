package vn.edu.topica.fcmclient;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by KJ Mok on 03/02/2017.
 */
public class MyFirebaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        //chay ham khi install app
        String token = FirebaseInstanceId.getInstance().getToken();
        luuTokenVaoCSDLRieng(token);
    }

    private void luuTokenVaoCSDLRieng(String token) {
        new FirebaseIDTask().execute(token);
    }
}
