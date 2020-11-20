package dk.tec.velfaerdsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.customavataradapter;
import Adapter.questionViewAdapter;
import Adapter.selectViewAdapter;


public class selectPage extends touchActivityHandler {

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
    }

    public class question {
        String name;
        String formula;
    }

    private void getImages(){

        selectImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        questions.add("Question 1");
        answers.add("5");

    }

    private void getGoodImages() {

        initRecyclerView();
    }

    private void getBadImages() {

        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGod = findViewById(R.id.recyclerViewGood);
        recyclerViewGod.setLayoutManager(layoutManagerGood);
        selectViewAdapter goodAdapter = new selectViewAdapter(this, questions, answers, selectImageUrls);
        recyclerViewGod.setAdapter(goodAdapter);

        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);
        selectViewAdapter badAdapter = new selectViewAdapter(this, questions, answers, selectImageUrls);
        recyclerViewBad.setAdapter(badAdapter);
    }
}