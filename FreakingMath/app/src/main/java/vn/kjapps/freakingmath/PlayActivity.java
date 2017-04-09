package vn.kjapps.freakingmath;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {

    private final int TIME_PLAY_EACH_LEVEL = 3000;
    private String[] arrColor = {"#689F38", "#0288D1", "#795548", "#607D8B", "#009688", "#009688"
            , "#9C27B0", "#616161", "#7C4DFF", "#455A64"};

    private Timer timer;
    private TimerTask timerTask;

    private int score = 0;
    private LevelModel model;
    private Random rd;

    TextView tvCongThuc, tvKetQua, tvScore;
    ImageButton btnCorrect, btnIncorrect;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.correctWrong == true){
                    score+=1;
                    nextLevel(score);
                } else {
                    showGameOver(score);
                }
            }
        });

        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.correctWrong == false){
                    score += 1;
                    nextLevel(score);
                } else {
                    showGameOver(score);
                }
            }
        });
    }

    private void nextLevel(int score) {
        tvScore.setText(String.valueOf(score));
        canelTimer();
        createTimerTask();
        model = GenerateLevel.generateLevel(score);
        displayNewLevel(model);
    }

    private void addControls() {
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_play);
        relativeLayout.setBackgroundColor(Color.DKGRAY);
        tvCongThuc = (TextView) findViewById(R.id.tvCongThuc);
        tvKetQua = (TextView) findViewById(R.id.tvKetQua);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnIncorrect = (ImageButton) findViewById(R.id.btnIncorrect);

        rd = new Random();
        model = GenerateLevel.generateLevel(1);
        displayNewLevel(model);
        createTimerTask();
    }

    private void createTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask,TIME_PLAY_EACH_LEVEL);
    }

    private void showGameOver(final int score) {
        btnCorrect.setEnabled(false);
        btnIncorrect.setEnabled(false);
        canelTimer();
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Your score: "+score)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        btnCorrect.setEnabled(true);
                        btnIncorrect.setEnabled(true);
                        tvScore.setText("0");
                        PlayActivity.this.score = 0;
                        PlayActivity.this.nextLevel(score);
                    }
                })
                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PlayActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void canelTimer() {
        timerTask.cancel();
        timer.cancel();
    }

    private void displayNewLevel(LevelModel model) {
        tvCongThuc.setText(model.strOperator);
        tvKetQua.setText(model.strResult);

        int indexColor = rd.nextInt(arrColor.length);
        relativeLayout.setBackgroundColor(Color.parseColor(arrColor[indexColor]));
    }
}
