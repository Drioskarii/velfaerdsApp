package dk.tec.velfaerdsapp;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

import javax.xml.transform.Result;

import Adapter.ResultAdapter;
import Adapter.VideoAdapter;
import Strengths.Points;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    private boolean videoWatched3 = false;
    AnimationDrawable animation;
    ImageView characterPlaceholder;
    TextView questionTxt;
    ImageView videobtn;
    PlayerView playerView;
    ImageView skipVideo;
    //vars
    ArrayList<Points> goodSelected = new ArrayList<>();
//    ArrayList<Points> badSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        videobtn = findViewById(R.id.btnYoutube);
        playerView = findViewById(R.id.player_view);
        skipVideo = findViewById(R.id.SkipVideo);
        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        VideoAdapter video = new VideoAdapter(ResultPage.this, R.raw.refvid, playerView);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        questionTxt = findViewById(R.id.select_txtQuestion);
        playerView.setVisibility(View.GONE);
        goodSelected.clear();
//        badSelected.clear();

        getValue();
        initRecyclerView();

        TextView txtSelectDinAvatar = findViewById(R.id.txtResultDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);

        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched7", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched7", false);
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.play();
            video.playVideo();
            editor.putBoolean("videoWatched7", videoWatched3 = true);
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

    }

    private void getValue() {
        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
//        badSelected = getIntent().getParcelableArrayListExtra("badSelectedList");

        System.out.println("HELP");
        System.out.println(goodSelected.size());
        System.out.println(goodSelected);
//        System.out.println(badSelected.size());
//        System.out.println(badSelected);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        ResultAdapter goodAdapter = new ResultAdapter(this, goodSelected , true);
        recyclerViewGood.setAdapter(goodAdapter);


/*        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        ResultAdapter badAdapter = new ResultAdapter(this, badSelected, false);
        recyclerViewBad.setAdapter(badAdapter);*/
    }
    protected void onRestart() {
        super.onRestart();

//        goodSelected.clear();
//        badSelected.clear();
    }
}