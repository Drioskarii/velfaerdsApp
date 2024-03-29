package dk.tec.velfaerdsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;

import Adapter.VideoAdapter;

public class MainActivity extends TouchActivityHandler {

    private static final String TAG = "mainActivity";
    Button btnBack, btnForward;
    PlayerView playerView;
    public static boolean tutDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sætter Variabler for main Activity
        playerView = findViewById(R.id.player_view);
        btnBack = findViewById(R.id.btn_main_back);
        btnForward = findViewById(R.id.btn_main_forward);

        //Sletter alle gemte variabler som var blevet sat før hen.
        SharedPreferences settings1 = getSharedPreferences("introValues", Context.MODE_PRIVATE);
        settings1.edit().clear().apply();
        SharedPreferences settings2 = getSharedPreferences("questionArray", Context.MODE_PRIVATE);
        settings2.edit().clear().apply();
        SharedPreferences settings3= getSharedPreferences("selectArray", Context.MODE_PRIVATE);
        settings3.edit().clear().apply();
        SharedPreferences settings4 = getSharedPreferences("emailArray", Context.MODE_PRIVATE);
        settings4.edit().clear().apply();

        //Starter VideoAdapteren som gør vi kan få startet en video.
        VideoAdapter video = new VideoAdapter(MainActivity.this, R.raw.intvid, playerView);
        video.play();
        video.playVideo();

        //Registreres Tilbage knap til at gå til "The24Strengths" siden
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newPage(MainActivity.this, The24Strengths.class));
                video.pauseVideo();
            }
        });

        //Registeres Fremmad knap til at gå til "IntroPage" som er den næste i programmet
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newPage(MainActivity.this, IntroPage.class));
                video.pauseVideo();
            }
        });
    }


    //Dette er en funktion som gør at der bliver åbnet en ny Activity som åbner en side med deres Logos Hjemmeside.
    public void logoClicked(View view) {
        if (tutDone) {
        String url = "";
        int id = view.getId();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

            if (id == R.id.imgLogoVfvoe)
                url = "https://videnscenterportalen.dk/vtoe/";
            else if (id == R.id.imgLogoTec)
                url = "https://www.tec.dk/";


        intent.setData(Uri.parse(url));
        startActivity(intent);
        }
    }
}