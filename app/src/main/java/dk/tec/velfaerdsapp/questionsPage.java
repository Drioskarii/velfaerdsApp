package dk.tec.velfaerdsapp;

import Adapter.questionViewAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class questionsPage extends touchActivityHandler {

    public ProgressBar questionsProgressBar;
    public static int count;
    public static int answered;
    LinearLayout questionsPageView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        questionsPageView = findViewById(R.id.questionsPageView);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);

        // questions list
        ArrayList<questionboxes> questionList = new ArrayList<>();
        questionList.add(new questionboxes(R.drawable.intellectual, "Question 1"));
        questionList.add(new questionboxes(R.drawable.people_person, "Question 2"));
        questionList.add(new questionboxes(R.drawable.sharp_brained, "Question 3"));
        questionList.add(new questionboxes(R.drawable.intellectual, "Question 4"));
        questionList.add(new questionboxes(R.drawable.people_person, "Question 5"));
        questionList.add(new questionboxes(R.drawable.sharp_brained, "Question 6"));

        RecyclerView mQuestionRecyclerView = findViewById(R.id.recyclerView);
        mQuestionRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapter = new questionViewAdapter(questionList);

        mQuestionRecyclerView.setLayoutManager(mLayoutManager);
        mQuestionRecyclerView.setAdapter(mAdapter);

        count = mAdapter.getItemCount();
        questionsProgressBar.setMax(mAdapter.getItemCount());
        questionsProgressBar.setProgress(answered);
    }
}