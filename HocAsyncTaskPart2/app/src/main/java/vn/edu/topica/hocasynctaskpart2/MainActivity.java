package vn.edu.topica.hocasynctaskpart2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnTai;
    ImageView imgHinh;
    ProgressDialog dialog;
    final String LINK_ANH = "http://k14.vcmedia.vn/thumb_w/600/2016/12509241-966534346767660-2844803451562581963-n-1457514920540.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTaiAnh();
            }
        });
    }

    private void xuLyTaiAnh() {
        ImageTask imgTask = new ImageTask();
        imgTask.execute(LINK_ANH);
    }

    private void addControls() {
        btnTai = (Button) findViewById(R.id.btnTai);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Đang tải, vui lòng chờ...!");
        dialog.setCanceledOnTouchOutside(false);//Khi nhấn ra ngoài activity thì dialog k bị ẩn
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            dialog.dismiss(); //Khác hàm cancel() nha
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try
            {
                String link = params[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            }
            catch (Exception ex) {
                Log.e("LỖI TẢI HÌNH", ex.toString());
            }
            return null;
        }
    }
}
