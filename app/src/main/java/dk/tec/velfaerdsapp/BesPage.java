package dk.tec.velfaerdsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ui.PlayerView;

import Adapter.VideoAdapter;
import Strengths.Strengths;
import QuestionsAdapter.BesAdapter;

//import com.google.android.material.slider.Slider;


public class BesPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    private boolean videoWatched1 = false;
    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answeredCount;
    Button btnBack, btnForward;
    ImageView videobtn;
    ImageView skipVideo;
    PlayerView playerView;
    ListView listOfQuestions;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bes_page);
        btnBack = findViewById(R.id.btn_bes_back);
        btnForward = findViewById(R.id.btn_bes_forward);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        skipVideo = findViewById(R.id.SkipVideo);
        videobtn = findViewById(R.id.btnYoutube);
        playerView = findViewById(R.id.player_view);
        BesAdapter questionsAdapter = new BesAdapter(BesPage.this, Strengths.getBesList());
        VideoAdapter video = new VideoAdapter(BesPage.this, R.raw.besvid, playerView);
        listOfQuestions.setAdapter(questionsAdapter);
        count = questionsAdapter.getCount();
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched1 = sharedPreferences.getBoolean("videoWatched", false);
        if (!videoWatched1){
            playerView.setVisibility(View.VISIBLE);
            video.play();
            video.playVideo();
            editor.putBoolean("videoWatched", videoWatched1 = true);
            editor.apply();
        }
        TextView txtDinAvatar = findViewById(R.id.txtBesDinAvatar);
        txtDinAvatar.setText(gJob + " " + gName);

        skipVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.GONE);
                skipVideo.setVisibility(skipVideo.GONE);
                video.pauseVideo();
            }
        });

        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.VISIBLE);
                skipVideo.setVisibility(skipVideo.VISIBLE);
                video.play();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answeredCount == count) {
                    startActivity(newPage(BesPage.this, TakPage.class));
                } else {
                    Toast.makeText(BesPage.this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}