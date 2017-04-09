package vn.edu.topica.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.topica.model.Contact;

public class TinNhanActivity extends AppCompatActivity {

    ListView lvTinNhan;
    List dsTinNhan;
    ArrayAdapter<String> adpTinNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_nhan);
        addControls();
        addEvents();
        docToanBoTinNhanTrongDienThoai();
    }

    private void docToanBoTinNhanTrongDienThoai() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor =getContentResolver().query(uri,null,null,null,null);
        dsTinNhan.clear();
        while(cursor.moveToNext()) {
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phoneNumber = cursor.getString(indexPhoneNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);

            dsTinNhan.add(phoneNumber+"\n"+timeStamp+"\n"+body);
        }
        cursor.close();
        adpTinNhan.notifyDataSetChanged();
    }

    private void addEvents() {
        
    }

    private void addControls() {
        lvTinNhan = (ListView) findViewById(R.id.lvTinNhan);
        dsTinNhan = new ArrayList<>();
        adpTinNhan = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dsTinNhan);
        lvTinNhan.setAdapter(adpTinNhan);
    }
}
