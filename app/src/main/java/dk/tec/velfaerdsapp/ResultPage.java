package dk.tec.velfaerdsapp;

import android.annotation.SuppressLint;
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
    ImageView characterPlaceholder;
    Button btnBack, btnForward;
    public boolean videoEnd = false;
    TextView questionTxt;
    ImageView videobtn;
    PlayerView playerView;
    ImageView skipVideo;
    //vars
    ArrayList<Points> goodSelected = new ArrayList<>();
    ArrayList<Points> singleSelected = new ArrayList<>();

    @SuppressLint("SetTextI18n")
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

        //Activivere vores Video Adapter da der bliver afspillet en video omkring sine bedste styrker
        VideoAdapter video = new VideoAdapter(ResultPage.this, R.raw.refvid, playerView);
        video.play();
        playerView.setVisibility(playerView.GONE);
        skipVideo.setVisibility(skipVideo.GONE);
        questionTxt = findViewById(R.id.select_txtQuestion);
        playerView.setVisibility(View.GONE);
        goodSelected.clear();

        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
        initRecyclerView();

        if (goodSelected.get(0).getPoints() == goodSelected.get(1).getPoints()){
            singleSelected = getIntent().getParcelableArrayListExtra("singleSelected");
            //Her checkes hvilket element der er blevet hentet og sætter ikonet så det passer.
            if (singleSelected.get(0).getTitle().contains("Mod")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_mod);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_mod);}}
            if (singleSelected.get(0).getTitle().contains("Nys")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_nys);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_nys);}}
            if (singleSelected.get(0).getTitle().contains("Bes")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_bes);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_bes);}}
            if (singleSelected.get(0).getTitle().contains("Tak")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_tak);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_tak);}}
            if (singleSelected.get(0).getTitle().contains("Sam")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_sam);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_sam);}}
            if (singleSelected.get(0).getTitle().contains("Soc")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_soc);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_soc);}}
        }
        else if (goodSelected.get(0).getPoints() > goodSelected.get(1).getPoints()){
            setIcon(0);
        }
        else if (goodSelected.get(1).getPoints() > goodSelected.get(0).getPoints()){
            setIcon(1);
        }

        TextView txtSelectDinAvatar = findViewById(R.id.txtResultDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);

        // Køre funktionen getValue, som vi laver længere nede i programmet.
        initRecyclerView();

        //Henter Oplysninger fra SharedPreferences, i dette tilfælge tjekkes der om du har set den video før. hvis ikke så afspilles den.
        SharedPreferences sharedPreferences = getSharedPreferences("videoWatched7", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        videoWatched3 = sharedPreferences.getBoolean("videoWatched7", false);
        if (!videoWatched3){
            playerView.setVisibility(View.VISIBLE);
            video.playVideo();
//            editor.putBoolean("videoWatched7", videoWatched3 = true);
//            editor.apply();
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

    private void setIcon(int position){
        if (goodSelected.get(position).getTitle().contains("Mod")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_mod);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_mod);}}
        if (goodSelected.get(position).getTitle().contains("Nys")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_nys);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_nys);}}
        if (goodSelected.get(position).getTitle().contains("Bes")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_bes);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_bes);}}
        if (goodSelected.get(position).getTitle().contains("Tak")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_tak);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_tak);}}
        if (goodSelected.get(position).getTitle().contains("Sam")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_sam);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_sam);}}
        if (goodSelected.get(position).getTitle().contains("Soc")){ if (gKøn == 1){characterPlaceholder.setImageResource(R.drawable.tndmand_soc);} else{characterPlaceholder.setImageResource(R.drawable.tndkvinde_soc);}}
    }

    //Dette er en funktion for selve udskiften af resultaten, hvordan siden ser ud.
    private void initRecyclerView() {

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        ResultAdapter goodAdapter = new ResultAdapter(this, goodSelected , true);
        recyclerViewGood.setAdapter(goodAdapter);
    }

    protected void onRestart() {
        super.onRestart();
    }
}