package dk.tec.velfaerdsapp;

import android.content.Intent;
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


public class selectPage extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 100;
    private GestureDetector gestureDetector;
    private static final String TAG = "selectpage";

    //vars
    private ArrayList<String> goodQuestions = new ArrayList<>();
    private ArrayList<String> goodAnswers = new ArrayList<>();
    private ArrayList<String> GoodselectmImageUrls = new ArrayList<>();

    private ArrayList<String> badQuestions = new ArrayList<>();
    private ArrayList<String> badAnswers = new ArrayList<>();
    private ArrayList<String> BadselectmImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);


        getGoodImages();
        getBadImages();

        //init gestureDetector
        this.gestureDetector = new GestureDetector(selectPage.this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            //press
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            //lift
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                //horizontal swipe
                float valueX = x2 - x1;
                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        backward();
                    } else {
                        forward();
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    public void forward() {
        Intent intent = new Intent(selectPage.this, emailPage.class);

        startActivity(intent);
    }

    public void backward() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getGoodImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        GoodselectmImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        goodQuestions.add("Question 1");
        goodAnswers.add("Good Answer 1");

        GoodselectmImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        goodQuestions.add("Question 2");
        goodAnswers.add("Good Answer 2");

        GoodselectmImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        goodQuestions.add("Question 3");
        goodAnswers.add("Good Answer 3");

        GoodselectmImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        goodQuestions.add("Question 4");
        goodAnswers.add("Good Answer 4");

        GoodselectmImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        goodQuestions.add("Question 5");
        goodAnswers.add("Good Answer 5");

        GoodselectmImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        goodQuestions.add("Question 6");
        goodAnswers.add("Good Answer 6");

        initRecyclerView();
    }

    private void getBadImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        BadselectmImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        badQuestions.add("Question 1");
        badAnswers.add("Bad Answer 1");

        BadselectmImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        badQuestions.add("Question 2");
        badAnswers.add("Bad Answer 2");

        BadselectmImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        badQuestions.add("Question 3");
        badAnswers.add("Bad Answer 3");

        BadselectmImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        badQuestions.add("Question 4");
        badAnswers.add("Bad Answer 4");

        BadselectmImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        badQuestions.add("Question 5");
        badAnswers.add("Bad Answer 5");

        initRecyclerView();

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGod = findViewById(R.id.recyclerViewGood);
        recyclerViewGod.setLayoutManager(layoutManagerGood);
        selectViewAdapter Goodadapter = new selectViewAdapter(this, goodQuestions,goodAnswers, GoodselectmImageUrls);
        recyclerViewGod.setAdapter(Goodadapter);

        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);
        selectViewAdapter Badadapter = new selectViewAdapter(this, badQuestions, badAnswers, BadselectmImageUrls);
        recyclerViewBad.setAdapter(Badadapter);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}