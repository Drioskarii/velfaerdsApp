package dk.tec.velfaerdsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

import javax.xml.transform.Result;

import Adapter.ResultAdapter;
import Adapter.SelectAdapter;
import Adapter.VideoAdapter;
import Strengths.Points;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    private boolean videoWatched3 = false;
    AnimationDrawable animation;
    ImageView characterPlaceholder;
    Button btnBack, btnForward;
    TextView questionTxt;
    ImageView videobtn;
    PlayerView playerView;
    ImageView skipVideo;
    //vars
    ArrayList<Points> Selected = new ArrayList<>();
    ArrayList<Points> Both = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        //Henter ID til Variablerne vi har oprettet.
        btnBack = findViewById(R.id.btn_result_back);
        btnForward = findViewById(R.id.btn_result_forward);
        videobtn = findViewById(R.id.btnYoutube);
        playerView = findViewById(R.id.player_view);
        skipVideo = findViewById(R.id.SkipVideo);

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        TouchActivityHandler tah = new TouchActivityHandler();
        //Her sættes avatar ikonet
        if (Selected.get(0).getTitle().contains("Mod")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_mod);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_mod);}}
        if (Selected.get(0).getTitle().contains("Nys")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_nys);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_nys);}}
        if (Selected.get(0).getTitle().contains("Bes")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_bes);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_bes);}}
        if (Selected.get(0).getTitle().contains("Tak")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_tak);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_tak);}}
        if (Selected.get(0).getTitle().contains("Sam")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_sam);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_sam);}}
        if (Selected.get(0).getTitle().contains("Soc")){ if (tah.gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_soc);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_soc);}}

        //Activivere vores Video Adapter da der bliver afspillet en video omkring sine bedste styrker
        VideoAdapter video = new VideoAdapter(ResultPage.this, R.raw.refvid, playerView);
        video.play();
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        questionTxt = findViewById(R.id.select_txtQuestion);
        playerView.setVisibility(View.GONE);
        Both.clear();

        getValue();
        initRecyclerView();

        TextView txtSelectDinAvatar = findViewById(R.id.txtResultDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);

        // Køre funktionen getValue, som vi laver længere nede i programmet.
        getValue();
        initRecyclerView();


        //Henter Oplysninger fra SharedPreferences, i dette tilfælge tjekkes der om du har set den video før. hvis ikke så afspilles den.
        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched7", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched7", false);
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.playVideo();
            editor.putBoolean("videoWatched7", videoWatched3 = true);
            editor.apply();
        }

        //sætter en knap til at pause en video.
        skipVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.GONE);
                skipVideo.setVisibility(skipVideo.GONE);
                video.pauseVideo();
            }
        });

        //sætter en knap til at have mulighed for at skippe videoen, dog kun hvis den får afvide fra sharedPreferences at man har set videoen før.
        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.VISIBLE);
                skipVideo.setVisibility(skipVideo.VISIBLE);

            }
        });

        //Knap til at gå tilbage i programmet
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        //Knap til at gå frem i programmet
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultPage.this, EmailPage.class);
                intent.putParcelableArrayListExtra("goodSelectedList", SelectAdapter.goodSelected);
                startActivity(intent);
                video.pauseVideo();
            }
        });
    }

    //en funktion som henter oplysninger fra de vælge styrker
    private void getValue() {
        Selected = getIntent().getParcelableArrayListExtra("SelectedList");
        Both = getIntent().getParcelableArrayListExtra("BothElements");
    }

    //Dette er en funktion for selve udskiften af resultaten, hvordan siden ser ud.
    private void initRecyclerView() {

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        ResultAdapter goodAdapter = new ResultAdapter(this, Both , true);
        recyclerViewGood.setAdapter(goodAdapter);

    }
    protected void onRestart() {
        super.onRestart();
    }
}