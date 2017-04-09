package vn.edu.topica.doctromtinnhan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] arrData = (Object[]) bundle.get("pdus"); //Tra ve mot mang objects
        for(int i=0;i<arrData.length;i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) arrData[i]);
            String noidung = smsMessage.getMessageBody();
            String phone = smsMessage.getOriginatingAddress(); //lay dia chi so sdt
            long time = smsMessage.getTimestampMillis();
            Date date = new Date(time);
            DateFormat format = new SimpleDateFormat("HH:mm:ss:SSSS");
            String datetime = format.format(date);
            Toast.makeText(context,"Phone number: "+phone+"\n Contents: "+noidung+"\nDate: "+datetime, Toast.LENGTH_SHORT).show();
        }
    }
}
