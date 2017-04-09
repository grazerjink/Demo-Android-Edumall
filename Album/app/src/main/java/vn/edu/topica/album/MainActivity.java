package vn.edu.topica.album;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button btnTruoc, btnSau;
    ImageView imgHinh;
    CheckBox chkTuDong;
    int currentPosition = -1;
    ArrayList<String> albums;

    Timer timer = null;
    TimerTask timerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHinhTruoc();
            }
        });

        btnSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHinhKeTiep();
            }
        });

        chkTuDong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    btnTruoc.setEnabled(false);
                    btnSau.setEnabled(false);
                    xuLyTuDongChayHinh();
                } else {
                    btnTruoc.setEnabled(true);
                    btnSau.setEnabled(true);
                    if(timer!=null) {
                        timer.cancel();
                    }
                }
            }
        });
    }

    private void xuLyTuDongChayHinh() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentPosition++;
                        if(currentPosition == albums.size())
                            currentPosition=0;
                        ImageTask task = new ImageTask();
                        task.execute(albums.get(currentPosition));
                    }
                });
            }
        };

        timer = new Timer();
        timer.schedule(timerTask,500,5000);
    }

    private void xuLyHinhTruoc() {
        currentPosition--;
        if (currentPosition == -1)
            currentPosition = albums.size()-1;
        ImageTask task = new ImageTask();
        task.execute(albums.get(currentPosition));
    }

    private void xuLyHinhKeTiep() {
        currentPosition++;
        if (currentPosition == albums.size())
            currentPosition = 0;
        ImageTask task = new ImageTask();
        task.execute(albums.get(currentPosition));
    }

    private void addControls() {
        btnTruoc = (Button) findViewById(R.id.btnTruoc);
        btnSau = (Button) findViewById(R.id.btnSau);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        chkTuDong = (CheckBox) findViewById(R.id.cbkTuDong);
        albums = new ArrayList<>();
        albums.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/16196024_1564039686944120_9122765256204813780_n.jpg?oh=e6f32158c8225dbd6bdb93ac5719d3cd&oe=59146211");
        albums.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15977713_1199396156782329_7499847505460771983_n.jpg?oh=450605e0bb48818f87bc0e6808515759&oe=59074C03");
        albums.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15826461_880941728714602_1577374016906230997_n.jpg?oh=7af47d99faed6a11e737b4589f1e74d4&oe=5914B281");
        albums.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15284948_1323608644347045_7421088488673835276_n.jpg?oh=0aeac07aa1458a52f9235bbcf8a53fc7&oe=591C25ED");
        albums.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/16406589_161874004302945_1398719861264986886_n.jpg?oh=1d38fe00034aedaf243732e0f531ca8e&oe=59451A63");
        currentPosition = 0;
        ImageTask task = new ImageTask();
        task.execute(albums.get(currentPosition));
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                return bitmap;
            } catch (Exception ex) {
                Log.e("ERROR_DOWNLOAD", ex.toString());
            }
            return null;
        }
    }
}