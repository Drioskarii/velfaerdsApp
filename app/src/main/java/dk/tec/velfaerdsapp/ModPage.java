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
import QuestionsAdapter.ModAdapter;

//import com.google.android.material.slider.Slider;


public class ModPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    private boolean videoWatched2 = false;
    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answeredCount;
    Button btnBack, btnForward;
    ImageView videobtn;
    PlayerView playerView;
    ListView listOfQuestions;
    ImageView skipVideo;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_page);
        btnBack = findViewById(R.id.btn_mod_back);
        btnForward = findViewById(R.id.btn_mod_forward);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        videobtn = findViewById(R.id.btnYoutube);
        skipVideo = findViewById(R.id.SkipVideo);
        playerView = findViewById(R.id.player_view);
        ModAdapter questionsAdapter = new ModAdapter(ModPage.this, Strengths.getModList());
        VideoAdapter video = new VideoAdapter(ModPage.this, R.raw.modvid, playerView);
        listOfQuestions.setAdapter(questionsAdapter);
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        TextView txtDinAvatar = findViewById(R.id.txtModDinAvatar);
        txtDinAvatar.setText(gJob + " " + gName);



        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched3", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched2 = sharedPreferences.getBoolean("videoWatched3", false);
        if (!videoWatched2){
            playerView.setVisibility(View.VISIBLE);
            video.play();
            video.playVideo();
            editor.putBoolean("videoWatched3", videoWatched2 = true);
            editor.apply();
        }

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
                    startActivity(newPage(ModPage.this, NysPage.class));
                    video.pauseVideo();
                } else {
                    Toast.makeText(ModPage.this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}