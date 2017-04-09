package vn.edu.topica.hocasynctaskpart1;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtSoButton;
    Button btnVe;
    ProgressBar progressBarPercent;
    LinearLayout layoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyVeButtonThoiGianThuc();
            }
        });
    }

    private void xuLyVeButtonThoiGianThuc() {
        int n = Integer.parseInt(edtSoButton.getText().toString());
        ButtonTask task = new ButtonTask();
        task.execute(n);
    }

    private void addControls() {
        edtSoButton = (EditText) findViewById(R.id.edtSoButton);
        btnVe = (Button) findViewById(R.id.btnVeButton);
        progressBarPercent = (ProgressBar) findViewById(R.id.progressBarPercent);
        layoutButton = (LinearLayout) findViewById(R.id.layoutButton);
    }

    class ButtonTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {
            //Khi bắt đầu vào thì nó khởi động
            super.onPreExecute();
            layoutButton.removeAllViews();
            progressBarPercent.setProgress(0);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int n = params[0];
            Random rd = new Random();
            for(int i=0; i<n;i++) {
                int percent = i*100/n;
                int value = rd.nextInt(500);
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[1];
            int percent = values[0];
            progressBarPercent.setProgress(percent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            Button btn = new Button(MainActivity.this);
            btn.setLayoutParams(params);
            btn.setText(value+"");
            layoutButton.addView(btn);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarPercent.setProgress(100);
        }
    }
}
