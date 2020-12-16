package dk.tec.velfaerdsapp;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.ResultAdapter;
import Adapter.SelectAdapter;
import Strengths.Strengths;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    AnimationDrawable animation;
    ImageView characterPlaceholder;
    //vars
    private ArrayList<String> goodQuestions = new ArrayList<>();
    private ArrayList<String> goodAnswers = new ArrayList<>();
    private ArrayList<String> goodSelectImageUrls = new ArrayList<>();
    private ArrayList<String> badQuestions = new ArrayList<>();
    private ArrayList<String> badAnswers = new ArrayList<>();
    private ArrayList<String> badSelectImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();

        getValue();
        initRecyclerView();

        TextView txtSelectDinAvatar = findViewById(R.id.txtResultDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);
    }

    private void getValue() {
        ArrayList<Strengths> goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
        ArrayList<Strengths> badSelected = getIntent().getParcelableArrayListExtra("badSelectedList");
        int goodSize = goodSelected.size();
        int badSize = badSelected.size();
        int iGood = 0;
        int iBad = 0;

        System.out.println("HELP");
        System.out.println(goodSelected.size());
        System.out.println(goodSelected);
        System.out.println(badSelected.size());
        System.out.println(badSelected);

        while (iGood < goodSize ){
            //Den her metode virker kun med billeder som ikke er i xml form
            goodSelectImageUrls.add(String.valueOf(Uri.parse("android.resource://dk.tec.velfaerdsapp/"+ goodSelected.get(iGood).getIcon())));
            goodQuestions.add(goodSelected.get(iGood).getQuestion());
            goodAnswers.add(String.valueOf(goodSelected.get(iGood).getAnswer()));
            iGood++;
        }

        while (iBad < badSize ){
            //Den her metode virker kun med billeder som ikke er i xml form
            badSelectImageUrls.add(String.valueOf(Uri.parse("android.resource://dk.tec.velfaerdsapp/"+ badSelected.get(iBad).getIcon())));
            badQuestions.add(badSelected.get(iBad).getQuestion());
            badAnswers.add(String.valueOf(badSelected.get(iBad).getAnswer()));
            iBad++;
        }
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

        ResultAdapter goodAdapter = new ResultAdapter(this, goodQuestions, goodAnswers, goodSelectImageUrls, true);
        recyclerViewGood.setAdapter(goodAdapter);


        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        ResultAdapter badAdapter = new ResultAdapter(this, badQuestions, badAnswers, badSelectImageUrls, false);
        recyclerViewBad.setAdapter(badAdapter);



    }
}