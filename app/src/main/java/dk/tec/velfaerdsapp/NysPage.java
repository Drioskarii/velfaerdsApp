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
import QuestionsAdapter.NysAdapter;

//import com.google.android.material.slider.Slider;


public class NysPage extends TouchActivityHandler {

    //Vars
    private boolean videoWatched3 = false;
    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answeredCount;
    Button btnBack, btnForward;
    ImageView videobtn;
    PlayerView playerView;
    ListView listOfQuestions;
    ImageView skipVideo;
    TextView txtDinAvatar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nys_page);

        //Binding til Objekter i XML
        btnBack = findViewById(R.id.btn_nys_back);
        btnForward = findViewById(R.id.btn_nys_forward);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        videobtn = findViewById(R.id.btnYoutube);
        skipVideo = findViewById(R.id.SkipVideo);
        playerView = findViewById(R.id.player_view);
        txtDinAvatar = findViewById(R.id.txtNysDinAvatar);

        //Sætter tekst i toppen af page.
        txtDinAvatar.setText(gJob + " " + gName);

        //Adapter til Spørgsmål
        NysAdapter questionsAdapter = new NysAdapter(NysPage.this, Strengths.getNysList());

        //Adapter til video + initialise af video
        VideoAdapter video = new VideoAdapter(NysPage.this, R.raw.nysvid, playerView);
        video.play();

        //Gemme video på page
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);

        //Binding til listen af spørgsmål, til question adapter.
        listOfQuestions.setAdapter(questionsAdapter);

        //Optælling af spørgsmål som brugeren har svaret
        count = questionsAdapter.getCount();

        //Opsætning af progress bar på spørgsmål
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);

        //Opsætning af shared prefs til video, så den kun kører på entry første gang.
        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched2", false);

        //Starter videoen på entry, hvis man ikke har set videoen før.
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.playVideo();
        }

        //Knap til at skippe videoen
        skipVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.GONE);
                skipVideo.setVisibility(skipVideo.GONE);
                video.pauseVideo();
            }
        });

        //Knap til at starte videoen, hvis den er lukket ned.
        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.VISIBLE);
                skipVideo.setVisibility(skipVideo.VISIBLE);

            }
        });

        // Listener til back button (Gå tilbage til forrige page)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        // Listener til forward button (Gå frem til næste page)
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sætter sharedpref når vidoen slutter.
                if (video.videoEnd)
                {
                    editor.putBoolean("videoWatched2", videoWatched3 = true);
                    editor.apply();
                }

                //Condition til at videoen skal ses færdig før man går videre.
                if(!video.videoEnd && !videoWatched3) {
                    Toast.makeText(NysPage.this, "Se videoen færdig for at fortsætte", Toast.LENGTH_SHORT).show();
                    System.out.println(video.videoEnd);
                }

                //Condition til at man kan gå videre hvis alle spørgsmål er svaret.
                else if (answeredCount == count) {
                    startActivity(newPage(NysPage.this, BesPage.class));
                    video.pauseVideo();
                }

                //Condition til hvis ikke alle spørgsmål er blevet besvaret.
                else {
                    Toast.makeText(NysPage.this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}