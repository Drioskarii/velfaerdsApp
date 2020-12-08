package dk.tec.velfaerdsapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.SelectAdapter;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    AnimationDrawable animation;
    ImageView characterPlaceholder;
    //vars
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<String> selectImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();



        initRecyclerView();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }

    private void initRecyclerView() {
//        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
//        recyclerViewGood.setLayoutManager(layoutManagerGood);
//        SelectAdapter goodAdapter = new SelectAdapter(this, questions, answers, selectImageUrls);
//        recyclerViewGood.setAdapter(goodAdapter);
//
//
//
//
//        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
//        recyclerViewBad.setLayoutManager(layoutManagerBad);
//        SelectAdapter badAdapter = new SelectAdapter(this, questions, answers, selectImageUrls);
//        recyclerViewBad.setAdapter(badAdapter);



    }
}