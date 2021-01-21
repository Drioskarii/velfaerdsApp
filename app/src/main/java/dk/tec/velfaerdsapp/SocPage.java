package dk.tec.velfaerdsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
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
import QuestionsAdapter.BesAdapter;
import QuestionsAdapter.ModAdapter;
import QuestionsAdapter.NysAdapter;
import QuestionsAdapter.SamAdapter;
import QuestionsAdapter.TakAdapter;
import Strengths.Strengths;
import QuestionsAdapter.SocAdapter;

//import com.google.android.material.slider.Slider;


public class SocPage extends TouchActivityHandler {

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
        setContentView(R.layout.activity_soc_page);
        btnBack = findViewById(R.id.btn_soc_back);
        btnForward = findViewById(R.id.btn_soc_forward);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        videobtn = findViewById(R.id.btnYoutube);
        skipVideo = findViewById(R.id.SkipVideo);
        playerView = findViewById(R.id.player_view);
        SocAdapter questionsAdapter = new SocAdapter(SocPage.this, Strengths.getSocList());
        VideoAdapter video = new VideoAdapter(SocPage.this, R.raw.socvid, playerView);
        listOfQuestions.setAdapter(questionsAdapter);
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        TextView txtDinAvatar = findViewById(R.id.txtSocDinAvatar);
        txtDinAvatar.setText(gJob + " " + gName);


        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched5", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched5", false);
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.play();
            video.playVideo();
            editor.putBoolean("videoWatched5", videoWatched3 = true);
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
                if (SocPage.answeredCount == SocPage.count) {
                    Intent intent = new Intent(SocPage.this, SelectPage.class);
                    intent.putParcelableArrayListExtra("ModList", ModAdapter.strengths);
                    intent.putParcelableArrayListExtra("NysList", NysAdapter.strengths);
                    intent.putParcelableArrayListExtra("BesList", BesAdapter.strengths);
                    intent.putParcelableArrayListExtra("TakList", TakAdapter.strengths);
                    intent.putParcelableArrayListExtra("SamList", SamAdapter.strengths);
                    intent.putParcelableArrayListExtra("SocList", SocAdapter.strengths);
                    video.pauseVideo();
                    startActivity(intent);
                } else {
                    Toast.makeText(SocPage.this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}