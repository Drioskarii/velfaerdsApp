package dk.tec.velfaerdsapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import Adapter.QuestionsAdapter;
import Adapter.SelectAdapter;


public class SelectPage extends TouchActivityHandler {


    private static final String TAG = "selectPage";
    //vars
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<String> selectImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);


        getImages();
        getGoodImages();
        getBadImages();

        ProgressBar gProgressBar = findViewById(R.id.goodProgressBar);
        ProgressBar bProgressBar = findViewById(R.id.badProgressBar);

        bProgressBar.setMax(5);
        bProgressBar.setProgress(SelectAdapter.getCount());

        gProgressBar.setMax(5);
        gProgressBar.setProgress(SelectAdapter.getCount());
    }

    private void getImages(){
        ArrayList<Strengths> strengths = getIntent().getParcelableArrayListExtra("ObjectList");

        int size = strengths.size();
        int i = 0;
        System.out.println("HELP");
        System.out.println(strengths.size());
        System.out.println(strengths);

        while (i < size ){
            //Den her metode virker kun med billeder som ikke er i xml form
            selectImageUrls.add(String.valueOf(Uri.parse("android.resource://dk.tec.velfaerdsapp/"+ strengths.get(i).getIcon())));
            questions.add(strengths.get(i).getQuestion());
            answers.add(String.valueOf(strengths.get(i).getAnswer()));
            i++;
        }
    }


    private void getGoodImages() {
       // badProgressBar = findViewById(R.id.badProgressBar);
       // badProgressBar.setProgress(badAdapter.getCount());

        initRecyclerView();
    }

    private void getBadImages() {
       // goodProgressBar = findViewById(R.id.goodProgressBar);
       // goodProgressBar.setProgress(goodAdapter.getCount());
        initRecyclerView();
    }

    @SuppressLint("NewApi")
    private void initRecyclerView() {
        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        ArrayList<String> gAnswers =  answers;
        gAnswers.sort(Collections.reverseOrder());

        SelectAdapter goodAdapter = new SelectAdapter(this, questions, answers, selectImageUrls, true);

        recyclerViewGood.setAdapter(goodAdapter);




        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        ArrayList<String> bAnswers =  answers;
        Collections.sort(bAnswers);

        SelectAdapter badAdapter = new SelectAdapter(this, questions, answers, selectImageUrls, false);
        recyclerViewBad.setAdapter(badAdapter);
    }
}