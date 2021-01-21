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
import QuestionsAdapter.SamAdapter;

//import com.google.android.material.slider.Slider;


public class SamPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    private boolean videoWatched3 = false;
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
        setContentView(R.layout.activity_sam_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        btnBack = findViewById(R.id.btn_sam_back);
        btnForward = findViewById(R.id.btn_sam_forward);
        videobtn = findViewById(R.id.btnYoutube);
        skipVideo = findViewById(R.id.SkipVideo);
        playerView = findViewById(R.id.player_view);
        SamAdapter questionsAdapter = new SamAdapter(SamPage.this, Strengths.getSamList());
        VideoAdapter video = new VideoAdapter(SamPage.this, R.raw.samvid, playerView);
        video.play();
        listOfQuestions.setAdapter(questionsAdapter);
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        TextView txtDinAvatar = findViewById(R.id.txtSamDinAvatar);
        txtDinAvatar.setText(gJob + " " + gName);


        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched4", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched4", false);
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.playVideo();
            editor.putBoolean("videoWatched4", videoWatched3 = true);
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
                    startActivity(newPage(SamPage.this, SocPage.class));
                    video.pauseVideo();
                } else {
                    Toast.makeText(SamPage.this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}